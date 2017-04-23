pragma solidity ^0.4.0;

contract RouteMaster
{
    struct CheckIn
    {
        address phone_address;
        uint timestamp;
    }

    struct Beacon
    {
        address beacon_address;
        CheckIn check_in;
    }

    address owner;
    Beacon[] beacons;
    uint rule=2;

    function RouteMaster()
    {
         owner = msg.sender;
    }

    function kill()
    {
        if(msg.sender == owner)
        {
            selfdestruct(owner);
        }
    }

    function addNewBeacon(address _new_beacon_address) returns (uint)
    {
        if(msg.sender == owner)
        {
            CheckIn memory _checkIn = CheckIn({phone_address: msg.sender, timestamp: block.timestamp});
            beacons.push(Beacon({beacon_address: _new_beacon_address, check_in: _checkIn}));
        }
        return beacons.length;
    }

    function deleteBeacon(address _beacon_address) returns (uint)
    {
        if(msg.sender == owner)
        {
            for(uint i = 0; i < beacons.length; i++)
            {
                if(beacons[i].beacon_address == _beacon_address)
                {
                    delete beacons[i];
                }
            }
        }
        return beacons.length;
    }

    function replaceBeacon(address _old_beacon_address, address _new_beacon_address) returns (uint)
    {
        if(msg.sender == owner)
        {
            for(uint i = 0; i < beacons.length; i++)
            {
                if(beacons[i].beacon_address == _old_beacon_address)
                {
                    CheckIn memory _checkIn = CheckIn({phone_address: msg.sender, timestamp: block.timestamp});
                    beacons[i] = Beacon({beacon_address: _new_beacon_address, check_in: _checkIn});
                    return beacons.length;
                }
            }
            // Old beacon not found -- should we return an error?
        }
        return beacons.length;
    }

    function checkIn(address _beacon_address) returns (string)
    {
        if(msg.sender == owner)
        {
            for(uint i=0; i < beacons.length; i++)
            {
                if(beacons[i].beacon_address == _beacon_address)
                {
                    beacons[i].check_in = CheckIn({phone_address: msg.sender, timestamp: block.timestamp});
                    return "Check in successful";
                }
            }
        }
        else
        {
            // Alert if an unreognised address is checking in
            return "Check-in by unrecognised address "; //+ msg.sender + " at location " + _beacon_address;
        }
    }

    function changeOwner()
    {
        uint votes = 0;
        for(uint i=0; i < beacons.length; i++)
        {
            if(beacons[i].beacon_address == msg.sender)
            {
                votes++;
            }
        }
        if(votes == rule)
        {
            owner = msg.sender;
        }
    }

    function getNumberOfBeacons() constant returns (uint)
    {
        return beacons.length;
    }

    function getRule() constant returns (uint)
    {
        return rule;
    }

    function setRule(uint _new_rule) returns (uint)
    {
        if(msg.sender == owner)
        {
            rule = _new_rule;
        }
        return rule;
    }
}