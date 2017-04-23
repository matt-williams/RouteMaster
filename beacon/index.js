var ethereumRpcUrl = process.env.ETHEREUM_RPC_URL || 'http://localhost:8545';
var ethereumAccount = process.env.ETHEREUM_ACCOUNT;
console.log("Connecting to Ethereum at", ethereumRpcUrl);
console.log("Ethereum account is", ethereumAccount);

var Web3 = require('web3');
var web3 = new Web3();
web3.setProvider(new web3.providers.HttpProvider(ethereumRpcUrl));
var MyContract = web3.eth.contract(require('../contract/ABI.json'));

var tokens = [];
var crypto = require('crypto');
var child_process = require('child_process');
var util = require('util');
var localIp = require('ip').toBuffer(require('ip').address()).toString("hex");

function refreshToken() {
  crypto.randomBytes(8, function(err, buffer) {
    if (err) {
      console.log("Failed to get random token");
      setTimeout(refreshToken, 10000);
    } else {
      var token = buffer.toString('hex');
      tokens.splice(0, Math.max(tokens.length - 2, 0));
      tokens.push(token);
      console.log("Tokens now", tokens);
      var cmd = "hcitool -i hci0 cmd 0x08 0x0008 1a 0b ff 52 4d " + localIp.replace(/../g, function(x) {return x + " "}) + token.replace(/../g, function(x) {return x + " "});
      child_process.exec(cmd, function(err, stdout, stderr) {
        if (err) {
          console.log("Updating Bluetooth failed");
          console.log("  Command:", cmd);
          console.log("  stdout:", stdout);
          console.log("  stderr:", stderr);
        }
        setTimeout(refreshToken, 10000);
      });
    }
  });
}
refreshToken();

var senseColor = [255, 255, 255];
var sense = require("sense-hat-led");
sense.clear();
setInterval(function() {
  sense.clear(senseColor);
  senseColor[0] = Math.max(senseColor[0] - 32, 0);
  senseColor[1] = Math.max(senseColor[1] - 32, 0);
  senseColor[2] = Math.max(senseColor[2] - 32, 0);
}, 100);

var express = require('express');
var app = express()

app.get('/contracts/:contract', function(req, res) {
  var contract = req.params.contract;
  if (contract) {
    db.get("SELECT account FROM contracts where contract = ?", contract, function(err, row) {
      if (err) {
        console.log("Failed to SELECT", err);
	res.sendStatus(503);
      } else if (row) {
        res.json({account: row.account});
      } else {
        // TODO: Create new account for this contract
//        web3.eth.getAccounts(function(err, accounts) {
//          if (err) {
//            console.log("Failed to get accounts", err);
//	      res.sendStatus(503);
//          } else if (accounts.length > 0) {
//            var account = accounts[0];
            var account = ethereumAccount;
            db.run("INSERT INTO contracts (contract, account) VALUES (?, ?);", contract, account, function(err) {
              if (err) {
                console.log("Failed to INSERT", err);
                res.sendStatus(503);
              } else {
                res.json({account: account});
              }
            });
//          } else {
//            console.log("No accounts");
//            res.sendStatus(503);
//          }
//        });
      }
    });
  } else {
    res.sendStatus(400);
  }
});

app.post('/contracts/:contract', function(req, res) {
  var contract = req.params.contract;
  var account = req.query.account;
  var token = req.query.token; // TODO Check this
  senseColor = [0, 0, 255];
  if (contract && account) {
    console.log("Account " + account + "checking in for " + contract);
    db.get("SELECT account FROM contracts where contract = ?", contract, function(err, row) {
      if (err) {
        console.log("Failed to SELECT", err);
        senseColor = [255, 0, 0];
        res.sendStatus(503);
      } else if (row) {
        var myContract = MyContract.at(contract);
        myContract.checkIn.sendTransaction(account, {from: row.account}, function(err) {
          if (err) {
            console.log("Failed to call contract", err);
            senseColor = [255, 0, 0];
            res.sendStatus(503);
          } else {
            senseColor = [0, 255, 0];
            res.json({});
          }
        });
      } else {
        console.log("No row");
        senseColor = [255, 0, 0];
        res.sendStatus(404); // TODO Fix this backwardness - GET creates the contract (and account), and POST updates it!
      }
    });
  } else {
    senseColor = [255, 0, 0];
    res.sendStatus(400);
  }
});

var sqlite3 = require('sqlite3');
var db = new sqlite3.Database("beacon.db", sqlite3.OPEN_READWRITE | sqlite3.OPEN_CREATE, function(err) {
  if (err) {
    console.log("Failed to open database", err);
  } else {
    db.run("CREATE TABLE IF NOT EXISTS contracts (contract text PRIMARY KEY, account text) WITHOUT ROWID;", function(err) {
      if (err) {
        console.log("Failed to create table", err);
      } else {
        app.listen(8080, function () {
          console.log('Listening on port 8080');
        });
      }
    });
  }
});
