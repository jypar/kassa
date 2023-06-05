This application for create maney transfer transaction and process it
There are 2 or more cash desks that have their own balance.
A person comes to cash desk A, when making a transfer, indicates the amount, currency, full name of the sender and recipient, 
the number of the sender and recipient, a comment, etc.
When creating a transfer, the sender will be assigned a unique CODE and the status of the transfer becomes "CREATED". 
The sender communicates this code to the recipient.
The recipient comes to cash desk B and reports a unique CODE, if everything matches, the transfer is considered successful 
and the status changes to "ISSUED". The balances of the two cash desks are also changing.
All cash desks can see all transfers, but only those cash desks in which this transfer was created can see the unique CODE. 
There is a searching by transfer fields.
