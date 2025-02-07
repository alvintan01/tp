# User Guide

## Introduction

MediVault is a Command Line Interface (CLI) application that will help to 
manage medication supplies within a pharmacy. It will solve the issue of 
tracking the stocks manually, discrepancies between actual and physical 
stocks as well as make the user's life easier by keeping track of expired 
medication.
## Contents

* [Quick Start](#quick-start)
* [Features](#features)
  * [Add medication stock](#adding-a-medication-stock-add)
  * [Delete medication stock](#deleting-a-medication-stock-delete)
  * [Update medication stock](#updating-medication-stock-information-update)
  * [List medication stock](#listing-medication-stock--list)
  * [Purge data](#purging-existing-data--purge)
  * [Help](#showing-help-page--help)
  * [Exit](#exiting-medivault--exit)
* [FAQ](#faq)
* [Command Summary](#command-summary)

## Quick Start

1. Ensure that you have Java 11 or above installed.
2. Download the latest version of `MediVault.jar` from [here](https://github.com/AY2122S1-CS2113T-T10-1/tp/releases/tag/v1.0).

## Features
Notes about the commands:
* Parameters enclosed in `[]` are **one or more** optional parameters.
* Parameters enclosed in `{}` are **totally** optional parameters.
* Parameters you specify can be in any order.
  * E.g. `update i/1 q/100 m/200` and `update i/1 m/200 q/100` are both
  acceptable.
* MediVault ignores additional parameters provided when commands do not
require one.
* If you specify the same parameter multiple times, MediVault will accept 
the last occurrence.
  * E.g. `delete i/2 i/1`, MediVault interprets the command as `delete i/1`.
* MediVault's commands are case-insensitive.
* Dates in the `e/EXPIRY_DATE` field are in `DD-MM-YYYY` format.


### Adding a medication stock: `add`
Adds medication into the inventory.
* If medication exists, description and maximum quantity will be optional parameters. If you include `d/DESCRIPTION m/MAX_QUANTITY` parameter, it will be ignored, MediVault will add with the existing description and existing maximum quantity.

Format: `add n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE [d/DESCRIPTION m/MAX_QUANTITY]`

Example 1 (If medication exists): `add n/panadol p/5 q/50 e/19-09-2021`

Expected Output 1: 
```
Medication added: panadol
+====+=========+=======+==========+=============+==================================================+==============+
| ID |  NAME   | PRICE | QUANTITY | EXPIRY_DATE |                   DESCRIPTION                    | MAX_QUANTITY | 
+====+=========+=======+==========+=============+==================================================+==============+
| 1  | panadol | $5.00 |    50    | 19-09-2021  | BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS |     1000     | 
+----+---------+-------+----------+-------------+--------------------------------------------------+--------------+
```
Example 2 (If medication does not exists): `add n/vicodin q/10 p/10 e/02-11-2021 d/popular drug for treating pain m/500`

Expected Output 2:
```
Medication added: vicodin
+====+=========+========+==========+=============+================================+==============+
| ID |  NAME   | PRICE  | QUANTITY | EXPIRY_DATE |          DESCRIPTION           | MAX_QUANTITY | 
+====+=========+========+==========+=============+================================+==============+
| 2  | vicodin | $10.00 |    10    | 02-11-2021  | popular drug for treating pain |     500      | 
+----+---------+--------+----------+-------------+--------------------------------+--------------+
```

### Deleting a medication stock: `delete`
Deletes medication from the inventory by specifying stock Id.

Format: `delete i/STOCK_ID`

Example: `delete i/3`

Expected output:
```
Medication deleted: Stock_Id=3
```

### Updating medication stock information: `update`

Edit an existing medication information in the inventory.
* Edits the optional parameters specified by the stock Id that you input. 
  * The stock Id must exist in MediVault.
* You cannot update the stock Id.
* If you include the `n/NAME`, `d/DESCRIPTION` or `m/MAX_QUANTITY` parameter, 
MediVault updates all entries that has same existing medication name given the 
`i/STOCK_ID` with your input values for these parameters.

Format: `update i/STOCK_ID [n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE d/DESCRIPTION
m/MAX_QUANTITY]`

Example:
`update i/1 n/panadol p/20 q/50 e/01-12-2021 
d/Best medicine to cure headache, fever and pains m/100`

Expected output:
```
Updated! Number of rows affected: 1
+====+=========+========+==========+=============+=================================================+==============+
| ID |  NAME   | PRICE  | QUANTITY | EXPIRY_DATE |                   DESCRIPTION                   | MAX_QUANTITY | 
+====+=========+========+==========+=============+=================================================+==============+
| 1  | panadol | $20.00 |    50    | 01-12-2021  | Best medicine to cure headache, fever and pains |     100      | 
+----+---------+--------+----------+-------------+-------------------------------------------------+--------------+
```

### Listing medication stock : `list`
Lists all existing medication in the inventory.
* All parameters for list command are optional, you can choose to list medication by any of the parameters.
* Example 1 demonstrates the list of all medication without parameters.
* Example 2 demonstrates list by medication name.

Format: `list {i/STOCK_ID n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE d/DESCRIPTION m/MAX_QUANTITY sort/COLUMN_NAME rsort/COLUMN_NAME}`

Example 1: `list`

Expected output:
```
+====+==============+========+==========+=============+====================================================+==============+
| ID |     NAME     | PRICE  | QUANTITY | EXPIRY_DATE |                    DESCRIPTION                     | MAX_QUANTITY |
+====+==============+========+==========+=============+====================================================+==============+
| 1  |   PANADOL    | $20.00 |    20    | 13-09-2021  |  BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS  |     1000     |
+----+--------------+--------+----------+-------------+----------------------------------------------------+--------------+
| 2  |   PANADOL    | $20.00 |    10    | 14-09-2021  |  BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS  |     1000     |
+----+--------------+--------+----------+-------------+----------------------------------------------------+--------------+
| 3  |   VICODIN    | $10.00 |    20    | 30-09-2021  |    POPULAR DRUG FOR TREATING ACUTE OR CHRONIC      |     500      |
|    |              |        |          |             |         MODERATE TO MODERATELY SEVERE PAIN         |              |
+----+--------------+--------+----------+-------------+----------------------------------------------------+--------------+
| 4  | SIMVASTATIN  | $20.00 |    25    | 10-10-2021  |  TREATS HIGH CHOLESTEROL AND REDUCES THE RISK OF   |     800      |
|    |              |        |          |             |                       STROKE                       |              |
+----+--------------+--------+----------+-------------+----------------------------------------------------+--------------+
| 5  |  LISINOPRIL  | $20.00 |    25    | 15-10-2021  |          USED FOR TREATING HYPOTHYROIDISM          |     800      |
+----+--------------+--------+----------+-------------+----------------------------------------------------+--------------+
| 6  | AZITHROMYCIN | $20.00 |    35    | 15-10-2021  |     USED FOR TREATING EAR, THROAT, AND SINUS       |     100      |
|    |              |        |          |             |                     INFECTIONS                     |              |
+----+--------------+--------+----------+-------------+----------------------------------------------------+--------------+
```

Example 2: `list n/panadol`

Expected output:
```
+====+=========+========+==========+=============+==================================================+==============+
| ID |  NAME   | PRICE  | QUANTITY | EXPIRY_DATE |                   DESCRIPTION                    | MAX_QUANTITY |
+====+=========+========+==========+=============+==================================================+==============+
| 1  | PANADOL | $20.00 |    20    | 13-09-2021  | BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS |     1000     |
+----+---------+--------+----------+-------------+--------------------------------------------------+--------------+
| 2  | PANADOL | $20.00 |    10    | 14-09-2021  | BEST MEDICINE TO CURE HEADACHES, FEVER AND PAINS |     1000     |
+----+---------+--------+----------+-------------+--------------------------------------------------+--------------+
```

### Purging existing data : `purge`
Deletes all data in MediVault.

Format: `purge`

Example: `purge`

Expected output:
```
Are you sure you want to delete all data? (Y/N)
Y
All data has been cleared!
```

### Showing help page : `help`
Displays the command syntax of all accepted commands by Medivault.

Format:`help`

Example: `help`

Expected output:
```
Welcome to the help page.
Note that parameters in {curly braces} are optional.
Parameters in [square braces] indicate that at least one of the parameter(s) must be provided.
+=========+====================================================================================================================+
| COMMAND |                                                   COMMAND SYNTAX                                                   | 
+=========+====================================================================================================================+
|   add   | add n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE d/DESCRIPTION m/MAX_QUANTITY                                           | 
+---------+--------------------------------------------------------------------------------------------------------------------+
|  list   | list {i/STOCK_ID p/PRICE q/QUANTITY e/EXPIRY_DATE d/DESCRIPTION m/MAX_QUANTITY sort/COLUMN_NAME rsort/COLUMN NAME} | 
+---------+--------------------------------------------------------------------------------------------------------------------+
| update  | update i/STOCK_ID [n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE d/DESCRIPTION m/MAX_QUANTITY]                           | 
+---------+--------------------------------------------------------------------------------------------------------------------+
| delete  | delete i/STOCK_ID                                                                                                  | 
+---------+--------------------------------------------------------------------------------------------------------------------+
|  exit   | exit                                                                                                               | 
+---------+--------------------------------------------------------------------------------------------------------------------+
|  help   | help                                                                                                               | 
+---------+--------------------------------------------------------------------------------------------------------------------+
|  purge  | purge                                                                                                              | 
+---------+--------------------------------------------------------------------------------------------------------------------+
For more information, refer to User Guide: https://ay2122s1-cs2113t-t10-1.github.io/tp/
```

### Exiting MediVault : `exit`
Exits MediVault.

Format: `exit`

Example: `exit`

Expected output:
```
Quitting MediVault...
```

## FAQ

**Q**: How do I transfer my data to another computer? 

**A**: Unfortunately, MediVault does not support saving information to files in v1.0.

## Command Summary

Syntax | Command Parameters
------ | ------
add | `add n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE d/DESCRIPTION m/MAX_QUANTITY`
delete | `delete i/STOCK_ID`
update | `update i/STOCK_ID [n/NAME p/PRICE q/QUANTITY e/EXPIRY_DATE d/DESCRIPTION m/MAX_QUANTITY]`
list | `list {i/STOCK_ID p/PRICE q/QUANTITY e/EXPIRY_DATE d/DESCRIPTION m/MAX_QUANTITY sort/COLUMN_NAME rsort/COLUMN NAME}`
purge | `no parameters`
help | `no parameters`
exit | `no parameters`