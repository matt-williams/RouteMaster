var Web3 = require('web3');
var web3 = new Web3();
web3.setProvider(new web3.providers.HttpProvider('http://localhost:8545'));
var MyContract = web3.eth.contract(require('./abi.json'));

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
      var cmd = "hcitool -i hci0 cmd 0x08 0x0008 0c 0b ff 52 4d " + localIp.replace(/../g, function(x) {return x + " "}) + token.replace(/../g, function(x) {return x + " "});
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
        web3.eth.getAccounts(function(err, accounts) {
          if (err) {
            console.log("Failed to get accounts", err);
	    res.sendStatus(503);
          } else if (accounts.length > 0) {
            var account = accounts[0];
            db.run("INSERT INTO contracts (contract, account) VALUES (?, ?);", contract, account, function(err) {
              if (err) {
                console.log("Failed to INSERT", err);
                res.sendStatus(503);
              } else {
                res.json({account: account});
              }
            });
          } else {
            console.log("No accounts");
	    res.sendStatus(503);
          }
        });
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
  if (contract && account) {
    db.get("SELECT account FROM contracts where contract = ?", contract, function(err, row) {
      if (err) {
        console.log("Failed to SELECT", err);
        res.sendStatus(503);
      } else if (row) {
        var myContract = MyContract.at(contract);
        //myContract.doAction.sendTransaction({from: row.account}, function(err) {
        //  if (err) {
        //    console.log("Failed to call contract", err);
        //    res.sendStatus(503);
        //  } else {
            res.json({});
        //  }
        //});
      } else {
        console.log("No row");
        res.sendStatus(404); // TODO Fix this backwardness - GET creates the contract, and POST reads it!
      }
    });
  } else {
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
