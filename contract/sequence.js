// This is the interface
var abi = [{"constant":false,"inputs":[{"name":"_new_rule","type":"uint256"}],"name":"setRule","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_index","type":"uint256"}],"name":"getCheckedIn","outputs":[{"name":"","type":"address"},{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[],"name":"kill","outputs":[],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_beacon_address","type":"address"}],"name":"deleteBeacon","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[],"name":"changeOwner","outputs":[{"name":"","type":"bool"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getOwner","outputs":[{"name":"","type":"address"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_index","type":"uint256"}],"name":"getBeacon","outputs":[{"name":"","type":"address"},{"name":"","type":"address"},{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_old_beacon_address","type":"address"},{"name":"_new_beacon_address","type":"address"}],"name":"replaceBeacon","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getNumberOfBeacons","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getRule","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_new_beacon_address","type":"address"}],"name":"addNewBeacon","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_phone_address","type":"address"}],"name":"checkIn","outputs":[{"name":"","type":"string"}],"payable":false,"type":"function"},{"inputs":[],"payable":false,"type":"constructor"}]

// Create an instance of the contract
var route = eth.contract(abi).at('0x1e97e737b5a149118c20727d398c9705f0a7af1a');

// Store the addresses of the beacons
var beacon1 = '0xfc778ea23be4ce5fb12044c093e7c4bf491259b2';
var beacon2 = '0xb1d0cfbd7eabeed07aae524cc2dfae8c64b1f7c7';

// Check how many beacons are associated with the contract.  Expect: 0.
route.getNumberOfBeacons();

// Add new beacons
route.addNewBeacon.sendTransaction(beacon1, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE', gas:1000000});
route.addNewBeacon.sendTransaction(beacon2, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE', gas:1000000});

// Check how many beacons are associated with the contract.  Expect: 2.
route.getNumberOfBeacons();

// Check beacon information
route.getBeacon(0);
route.getBeacon(1);

// Check in via beacon
var beacon = eth.contract(abi).at('0x1e97e737b5a149118c20727d398c9705f0a7af1a');
var phone = '0x040ddcdbc365dc36b35e385ad6fd89b5f537cd3c';
beacon.checkIn.sendTransaction(phone, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE'});

// Check beacon information
route.getBeacon(0);
route.getBeacon(1);

// Check number of beacons required to change ownership
route.getRule();

// If desired, update number of beacons required to change ownership
route.setRule.sendTransaction(1, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE'});

// Check current contract owner
// Should be 0x320b320B322fdFc76894160C2F48448D7C940dDE

// Change ownership!
route.changeOwner.sendTransaction({from: '0x040ddcdbc365dc36b35e385ad6fd89b5f537cd3c', gas:1000000});

// Check new contract owner
// Should now be 0x040ddcdbc365dc36b35e385ad6fd89b5f537cd3c


var sender = '0x320b320B322fdFc76894160C2F48448D7C940dDE'
var receiver = '0x040ddcdbc365dc36b35e385ad6fd89b5f537cd3c'
var amount = web3.toWei(0.01, "ether")
eth.sendTransaction({from:sender, to:receiver, value:amount});