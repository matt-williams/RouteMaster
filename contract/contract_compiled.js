var routemaster_sol_routemasterContract = web3.eth.contract([{"constant":false,"inputs":[{"name":"_new_rule","type":"uint256"}],"name":"setRule","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_index","type":"uint256"}],"name":"getCheckedIn","outputs":[{"name":"","type":"address"},{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[],"name":"kill","outputs":[],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_beacon_address","type":"address"}],"name":"deleteBeacon","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[],"name":"changeOwner","outputs":[],"payable":false,"type":"function"},{"constant":true,"inputs":[{"name":"_index","type":"uint256"}],"name":"getBeacon","outputs":[{"name":"","type":"address"},{"name":"","type":"address"},{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_old_beacon_address","type":"address"},{"name":"_new_beacon_address","type":"address"}],"name":"replaceBeacon","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getNumberOfBeacons","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":true,"inputs":[],"name":"getRule","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_new_beacon_address","type":"address"}],"name":"addNewBeacon","outputs":[{"name":"","type":"uint256"}],"payable":false,"type":"function"},{"constant":false,"inputs":[{"name":"_phone_address","type":"address"}],"name":"checkIn","outputs":[{"name":"","type":"string"}],"payable":false,"type":"function"},{"inputs":[],"payable":false,"type":"constructor"}]);
var routemaster_sol_routemaster = routemaster_sol_routemasterContract.new(
   {
     from: web3.eth.accounts[0], 
     data: '0x60606040526002600255341561001157fe5b5b33600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b5b610f1c806100646000396000f300606060405236156100ad576000357c0100000000000000000000000000000000000000000000000000000000900463ffffffff168063202b85af146100af578063383558e6146100e357806341c0e1b51461014a578063596a29581461015c57806362a09477146101a657806390caecc2146101b8578063a5f4e4d614610252578063a814b2d9146102bb578063b78ba7c8146102e1578063c9564cd414610307578063d9a59e3314610351575bfe5b34156100b757fe5b6100cd600480803590602001909190505061040e565b6040518082815260200191505060405180910390f35b34156100eb57fe5b6101016004808035906020019091905050610479565b604051808373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018281526020019250505060405180910390f35b341561015257fe5b61015a6104e0565b005b341561016457fe5b610190600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610574565b6040518082815260200191505060405180910390f35b34156101ae57fe5b6101b66106f7565b005b34156101c057fe5b6101d660048080359060200190919050506107f1565b604051808473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1681526020018373ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff168152602001828152602001935050505060405180910390f35b341561025a57fe5b6102a5600480803573ffffffffffffffffffffffffffffffffffffffff1690602001909190803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610883565b6040518082815260200191505060405180910390f35b34156102c357fe5b6102cb610abd565b6040518082815260200191505060405180910390f35b34156102e957fe5b6102f1610acb565b6040518082815260200191505060405180910390f35b341561030f57fe5b61033b600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610ad6565b6040518082815260200191505060405180910390f35b341561035957fe5b610385600480803573ffffffffffffffffffffffffffffffffffffffff16906020019091905050610c70565b60405180806020018281038252838181518152602001915080519060200190808383600083146103d4575b8051825260208311156103d4576020820191506020810190506020830392506103b0565b505050905090810190601f1680156104005780820380516001836020036101000a031916815260200191505b509250505060405180910390f35b6000600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141561046e57816002819055505b60025490505b919050565b600060006000600060018581548110151561049057fe5b906000526020600020906003020160005b5091508160010190508060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168160010154935093505b5050915091565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff16141561057157600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff16ff5b5b565b60006000600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff1614156106e857600090505b6001805490508110156106e7578273ffffffffffffffffffffffffffffffffffffffff1660018281548110151561060657fe5b906000526020600020906003020160005b5060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156106d95760018181548110151561066757fe5b906000526020600020906003020160005b6000820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556001820160006000820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556001820160009055505050505b5b80806001019150506105d3565b5b60018054905091505b50919050565b6000600060009150600090505b6001805490508110156107a0573373ffffffffffffffffffffffffffffffffffffffff1660018281548110151561073757fe5b906000526020600020906003020160005b5060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff1614156107925781806001019250505b5b8080600101915050610704565b6002548214156107ec5733600060006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055505b5b5050565b6000600060006000600060018681548110151561080a57fe5b906000526020600020906003020160005b5091508160010190508160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff168160000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1682600101549450945094505b50509193909250565b6000600061088f610dfb565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415610aac57600091505b600180549050821015610aab578473ffffffffffffffffffffffffffffffffffffffff1660018381548110151561091d57fe5b906000526020600020906003020160005b5060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610a9d576040604051908101604052803373ffffffffffffffffffffffffffffffffffffffff1681526020014281525090506040604051908101604052808573ffffffffffffffffffffffffffffffffffffffff168152602001828152506001838154811015156109d857fe5b906000526020600020906003020160005b5060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010160008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506020820151816001015550509050506001805490509250610ab5565b5b81806001019250506108ea565b5b60018054905092505b505092915050565b600060018054905090505b90565b600060025490505b90565b6000610ae0610dfb565b600060009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff163373ffffffffffffffffffffffffffffffffffffffff161415610c61576040604051908101604052803373ffffffffffffffffffffffffffffffffffffffff16815260200142815250905060018054806001018281610b789190610e2c565b916000526020600020906003020160005b6040604051908101604052808773ffffffffffffffffffffffffffffffffffffffff16815260200185815250909190915060008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010160008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff1602179055506020820151816001015550505050505b60018054905091505b50919050565b610c78610e5e565b6000600090505b600180549050811015610df4573373ffffffffffffffffffffffffffffffffffffffff16600182815481101515610cb257fe5b906000526020600020906003020160005b5060000160009054906101000a900473ffffffffffffffffffffffffffffffffffffffff1673ffffffffffffffffffffffffffffffffffffffff161415610de6576040604051908101604052808473ffffffffffffffffffffffffffffffffffffffff16815260200142815250600182815481101515610d3f57fe5b906000526020600020906003020160005b5060010160008201518160000160006101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff16021790555060208201518160010155905050604060405190810160405280601381526020017f436865636b20696e207375636365737366756c000000000000000000000000008152509150610df5565b5b8080600101915050610c7f565b5b50919050565b604060405190810160405280600073ffffffffffffffffffffffffffffffffffffffff168152602001600081525090565b815481835581811511610e5957600302816003028360005260206000209182019101610e589190610e72565b5b505050565b602060405190810160405280600081525090565b610eed91905b80821115610ee95760006000820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556001820160006000820160006101000a81549073ffffffffffffffffffffffffffffffffffffffff02191690556001820160009055505050600301610e78565b5090565b905600a165627a7a7230582091542eaae163b7088d3acadc9b17578f32ea72937b436ebe5a44a00c618509c90029', 
     gas: '4700000'
   }, function (e, contract){
    console.log(e, contract);
    if (typeof contract.address !== 'undefined') {
         console.log('Contract mined! address: ' + contract.address + ' transactionHash: ' + contract.transactionHash);
    }
 })