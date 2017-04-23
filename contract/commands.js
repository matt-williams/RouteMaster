var abi = [{"constant":false,"inputs":[{"name":"_new_rule","type":"uint256"}],"name":"setRule","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[],"name":"kill","outputs":[],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_beacon_address","type":"address"}],"name":"deleteBeacon","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[],"name":"changeOwner","outputs":[],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_old_beacon_address","type":"address"},{"name":"_new_beacon_address","type":"address"}],"name":"replaceBeacon","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getNumberOfBeacons","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getRule","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_new_beacon_address","type":"address"}],"name":"addNewBeacon","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_phone_address","type":"address"}],"name":"checkIn","outputs":[{"name":"","type":"string"}],"payable":false,"type":"function"},{"inputs":[],"payable":false,"type":"constructor"}]

var route = eth.contract(abi).at('0xccdc5985455efa0ee51a9cdb9b8a48d7a2e00998');
var beacon = eth.contract(abi).at('0xccdc5985455efa0ee51a9cdb9b8a48d7a2e00998');

personal.unlockAccount(web3.eth.accounts[0], "secret");
route.setRule.sendTransaction(3, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE'});
route.getRule();

var beacon1 = '0xac96984e3360f745c240345b1b630edbbca27342';
var beacon2 = '0x30a55d031db5e4d1ca27a803684a91fa422c9aaf';
route.getNumberOfBeacons();
route.addNewBeacon.sendTransaction(beacon1, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE'});
route.replaceBeacon.sendTransaction(beacon1, beacon2, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE'});
route.deleteBeacon.sendTransaction(beacon2, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE'});

var phone = '0x0xccdc5985455efa0ee51a9cdb9b8a48d7a2e00998';
beacon.checkIn.sendTransaction(phone, {from: '0x320b320B322fdFc76894160C2F48448D7C940dDE'});