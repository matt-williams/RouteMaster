# Beacon server

This server runs on the beacons and exposes a combined BLE and HTTP service for updating locations.

It exposes 2 operations.

*   `GET /contracts/<contract id>` - Gets (creating if necessary) the beacon's account for this contract, and returns it as JSON, e.g. `{"account":"0xe5776bb0a1cf1fddf0247f0f4bcaa2736013f981"}`.

*   `POST /contracts/<contract id>?account=<account id>` - Triggers an update to the contract for this beacon for the specified user account.  Returns 200 OK and an empty JSON object on success.

Note that in all cases `<contract id>` and `<account id>` must be hex-encoded and prefixed with `0x`, e.g. `0xe5776bb0a1cf1fddf0247f0f4bcaa2736013f981`.
