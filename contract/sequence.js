// This is the interface
var abi = [{"constant":false,"inputs":[{"name":"_new_rule","type":"uint256"}],"name":"setRule","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_index","type":"uint256"}],"name":"getCheckedIn","outputs":[{"name":"","type":"address"},{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[],"name":"kill","outputs":[],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_beacon_address","type":"address"}],"name":"deleteBeacon","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[],"name":"changeOwner","outputs":[{"name":"","type":"bool"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getOwner","outputs":[{"name":"","type":"address"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_index","type":"uint256"}],"name":"getBeacon","outputs":[{"name":"","type":"address"},{"name":"","type":"address"},{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_old_beacon_address","type":"address"},{"name":"_new_beacon_address","type":"address"}],"name":"replaceBeacon","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getNumberOfBeacons","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getRule","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_new_beacon_address","type":"address"}],"name":"addNewBeacon","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[],"name":"eraseBeacons","outputs":[{"name":"","type":"bool"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_phone_address","type":"address"}],"name":"checkIn","outputs":[{"name":"","type":"string"}],"payable":false,"type":"function"},{"inputs":[],"payable":false,"type":"constructor"}]

// Create an instance of the contract
var route = eth.contract(abi).at('0xbb297ff77c642fd73a8b21010d58eb8d6f12b51f');

// Store the addresses of the beacons
var beacon1 = '0xfc778ea23be4ce5fb12044c093e7c4bf491259b2';
var beacon2 = '0xb1d0cfbd7eabeed07aae524cc2dfae8c64b1f7c7';

// @@@ Erase old beacons?
route.eraseBeacons.sendTransaction({from: '0x320b320B322fdFc76894160C2F48448D7C940dDE'});

// Add new beacons
route.addNewBeacon.sendTransaction(beacon1, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE', gas:1000000});
route.addNewBeacon.sendTransaction(beacon2, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE', gas:1000000});
route.checkIn.sendTransaction('0x0000000000000000000000000000000000000000', {from: beacon1});
route.checkIn.sendTransaction('0x0000000000000000000000000000000000000000', {from: beacon2});

// Update rule
route.setRule.sendTransaction(2, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE'});

// START DEMO FROM HERE

// Check in via beacons
// Only one check-in
curl -X POST http://10.1.104.78:8080/contracts/0xbb297ff77c642fd73a8b21010d58eb8d6f12b51f?account=0x040ddcdbc365dc36b35e385ad6fd89b5f537cd3c

// WHILE BLOCK IS BEING MINED

// Check how many beacons are associated with the contract.  Expect: 0.
// route.getNumberOfBeacons();

// Check beacon information.  Expect no information.
route.getBeacon(0);
route.getBeacon(1);

// EXPLAIN ABOUT CONFIGURATION
// Add new beacons
// route.addNewBeacon.sendTransaction(beacon1, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE', gas:1000000});
// route.addNewBeacon.sendTransaction(beacon2, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE', gas:1000000});
// route.setRule.sendTransaction(2, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE'});
// Update rule
// route.setRule.sendTransaction(2, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE'});

// Check number of beacons required to change ownership.  Expect 2.
route.getRule();

// Check current contract owner
// Should be 0x320b320B322fdFc76894160C2F48448D7C940dDE
route.getOwner();

// WAIT FOR BLOCK TO BE MINED

// Check beacon information. Expect one check-in.
route.getBeacon(0);
route.getBeacon(1);

// NEW PHONE
// Only one check-in through phone
// curl -X POST http://10.1.104.78:8080/contracts/0xbb297ff77c642fd73a8b21010d58eb8d6f12b51f?account=0x040ddcdbc365dc36b35e385ad6fd89b5f537cd3c
// WAIT FOR BLOCK TO BE MINED

// EXPLAIN ABOUT BLE

// Check beacon information. Expect one check-in but changed from previous.
route.getBeacon(0);
route.getBeacon(1);

// Check current contract owner
// Should still be 0x320b320B322fdFc76894160C2F48448D7C940dDE
route.getOwner();

// Try to change ownership
route.changeOwner.sendTransaction({from: '0x040ddcdbc365dc36b35e385ad6fd89b5f537cd3c', gas:1000000});

// Add one more check-in through phone
// curl -X POST http://10.1.106.128:8080/contracts/0xbb297ff77c642fd73a8b21010d58eb8d6f12b51f?account=0x320b320B322fdFc76894160C2F48448D7C940dDE

// Try to change ownership
route.changeOwner.sendTransaction({from: '0x040ddcdbc365dc36b35e385ad6fd89b5f537cd3c', gas:1000000});
// WAIT FOR BLOCK TO BE MINED

// EXPLAIN ABOUT EXTENSIONS

// Check beacon information.  Expect two check-ins; both the same address.
route.getBeacon(0);
route.getBeacon(1);

// Check new contract owner
// Should now be 0x040ddcdbc365dc36b35e385ad6fd89b5f537cd3c
route.getOwner();

// YAY! Receovered ownership of your contract








var sender = '0x320b320B322fdFc76894160C2F48448D7C940dDE'
var receiver = '0xfc778ea23be4ce5fb12044c093e7c4bf491259b2'
var amount = web3.toWei(0.05, "ether")
eth.sendTransaction({from:sender, to:receiver, value:amount});