## Bank-API
It is a simple API server using Java and SpringBoot. With this API users can make transaction with currency.
## Requests
### /createAccount
It is POST request without params. Request body: name, surname, dateOfBith, seriesOfPassport, numberOfPassport. Response is uuid of new account.\
Example: `/createAccount`
### /getAccount
It is GET request. Params: uuid. Response is JSON of account with requested uuid.\
Example: `/getAccount?uuid=0fb388bc-4a46-4da4-8d57-81066a2fc3a4`
### /balance
It is GET request. Params: uuid, val. Response is amount requested currency.\
Example: `/balance?uuid=0fb388bc-4a46-4da4-8d57-81066a2fc3a4&val=USD`
### /put
It is POST request. Params: uuid, val, quantity. Put requested amount of requested currency on the account.\
Example: `/put?uuid=0fb388bc-4a46-4da4-8d57-81066a2fc3a4&val=USD&quantity=100`
### /withdraw
It is POST request. Params: uuid, val, quantity. Withdraws requested amount of requested currency from the account.\
Example: `/withdraw?uuid=0fb388bc-4a46-4da4-8d57-81066a2fc3a4&val=USD&quantity=100`
### /buy
It is POST request. Params: uuid, val, quantity. Buy requested amount of requested currency.\
Example: `/buy?uuid=0fb388bc-4a46-4da4-8d57-81066a2fc3a4&val=USD&quantity=100`
### /sell
It is POST request. Params: uuid, val, quantity. Sell requested amount of requested currency.\
Example: `/sell?uuid=0fb388bc-4a46-4da4-8d57-81066a2fc3a4&val=USD&quantity=100`
