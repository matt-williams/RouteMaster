var abi = [{"constant":false,"inputs":[{"name":"_new_rule","type":"uint256"}],"name":"setRule","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[],"name":"kill","outputs":[],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_beacon_address","type":"address"}],"name":"deleteBeacon","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[],"name":"changeOwner","outputs":[],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_old_beacon_address","type":"address"},{"name":"_new_beacon_address","type":"address"}],"name":"replaceBeacon","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getNumberOfBeacons","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getRule","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_new_beacon_address","type":"address"}],"name":"addNewBeacon","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_beacon_address","type":"address"}],"name":"checkIn","outputs":[{"name":"","type":"string"}],"payable":false,"type":"function"},{"inputs":[],"payable":false,"type":"constructor"}]

var route = eth.contract(abi).at('0x895EE966a516dbCf292aaA12e4339e4DbDCe8C0F');

personal.unlockAccount(web3.eth.accounts[0], "yourPassword");
route.setRule.sendTransaction(3, {'from: 0x320b320B322fdFc76894160C2F48448D7C940dDE'});
route.getRule();
