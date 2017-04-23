var abi = [{"constant":false,"inputs":[{"name":"_new_rule","type":"uint256"}],"name":"setRule","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_index","type":"uint256"}],"name":"getCheckedIn","outputs":[{"name":"","type":"address"},{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[],"name":"kill","outputs":[],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_beacon_address","type":"address"}],"name":"deleteBeacon","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[],"name":"changeOwner","outputs":[],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_index","type":"uint256"}],"name":"getBeacon","outputs":[{"name":"","type":"address"},{"name":"","type":"address"},{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_old_beacon_address","type":"address"},{"name":"_new_beacon_address","type":"address"}],"name":"replaceBeacon","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getNumberOfBeacons","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getRule","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_new_beacon_address","type":"address"}],"name":"addNewBeacon","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_phone_address","type":"address"}],"name":"checkIn","outputs":[{"name":"","type":"string"}],"payable":false,"type":"function"},{"inputs":[],"payable":false,"type":"constructor"}]

var route = eth.contract(abi).at('0x1e97e737b5a149118c20727d398c9705f0a7af1a');
var beacon = eth.contract(abi).at('0x1e97e737b5a149118c20727d398c9705f0a7af1a');

route.setRule.sendTransaction(1, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE'});
route.getRule();

var beacon1 = '0xfc778ea23be4ce5fb12044c093e7c4bf491259b2';
var beacon2 = '0xb1d0cfbd7eabeed07aae524cc2dfae8c64b1f7c7';

route.getNumberOfBeacons();
route.addNewBeacon.sendTransaction(beacon2, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE', gas:1000000});
route.replaceBeacon.sendTransaction(beacon1, beacon2, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE'});
route.deleteBeacon.sendTransaction(beacon2, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE'});
route.getBeacon(0);
route.getCheckedIn(0);

var phone = '0x0xccdc5985455efa0ee51a9cdb9b8a48d7a2e00998';
beacon.checkIn.sendTransaction(phone, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE'});