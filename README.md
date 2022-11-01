## Bank-API
It is a simple API server using Java and SpringBoot. With this API users can make transaction with currency.
## Requests
### /createAccount
It is POST request without params. Request body: name, surname, dateOfBith, seriesOfPassport, numberOfPassport. Response is uuid of new account.
Example: `/createAccount`
### /getAccount
It is GET request. Params: uuid. Response is JSON of account with requested uuid.
Example: `/getAccount?uuid=0fb388bc-4a46-4da4-8d57-81066a2fc3a4`
### /balance
It is GET request. Params: uuid, val()
Example: `/balance?uuid=0fb388bc-4a46-4da4-8d57-81066a2fc3a4&val=USD`
### /put
### /withdraw
### /buy
### /sell
