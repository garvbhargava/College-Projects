# Apparel Easy — Billing and Store Management

A Python coursework project for a garment retail shop. Two programs: a small invoice calculator, and a menu-driven store management system backed by CSV files.

> Previously titled Shivam Garments. The main program has always greeted users as **Apparel Easy**, and the whole project now uses that name.

## Project layout

```
README.md
Documentation/            Specifications (PDF)
ApparelEasy/              The two programs and their data
  G.S.M.S.py              Garment Store Management System
  Basic Billing.py        Standalone invoice calculator
  Customer Details.csv
  Stock Details.csv
  Billing Details.csv
  Login Id.csv
  Reports/                Exported reports are written here
```

## The two programs

| File | What it does |
| --- | --- |
| `ApparelEasy/Basic Billing.py` | A short invoice calculator. Takes a garment type, sub-type and price, applies the discount for that combination, adds GST and prints a formatted invoice. |
| `ApparelEasy/G.S.M.S.py` | The full Garment Store Management System — log in, then add, remove, modify, sort and search records, generate reports and plot charts. |

## Data files

Everything is stored in CSVs beside the scripts in `ApparelEasy/`. pandas reads them at startup and writes the changed file back after each change.

| File | Indexed by | Holds |
| --- | --- | --- |
| `Customer Details.csv` | `Cust_ID` | Name, gender, phone, email, date of birth, address, pin code |
| `Stock Details.csv` | `Stock_Id` | Garment name, vendor, quantity purchased, unit price, description, garment type, GST, total price |
| `Billing Details.csv` | `Bill_Id` | Bills raised, one row per item sold (a bill with several items has several rows) |
| `Login Id.csv` | `Login_ID` | Admin login credentials |

## Requirements

Python 3, with pandas 2 and matplotlib:

```bash
pip install "pandas<3" matplotlib
```

Checked with Python 3.13, pandas 2.2.3 and matplotlib 3.9.2. pandas 3 is not supported: creating a bill or changing a customer's phone number fails with a `TypeError`.

## Running it

```bash
cd ApparelEasy
python "G.S.M.S.py"
```

Run it from the `ApparelEasy` folder. The scripts open the CSVs by relative filename, so starting from a different working directory fails with `FileNotFoundError`.

The standalone calculator needs no data files:

```bash
python "Basic Billing.py"
```

## Menu

After signing in: **Add**, **Remove**, **Modify**, **Sort**, **Searching**, **Reports**, **Data Visualization**, **Logout**. Add, remove and modify each apply to customers, stock or bills. Record IDs are generated automatically in sequence (`A036`, `S036`, `B049`, …). Reports can be exported as CSV files into `ApparelEasy/Reports/`; the folder must exist (the export path uses a Windows separator, `Reports\`).

Every sale in the main program gets a flat 15% discount and 10% GST on the discounted amount. The calculator uses its own rules:

| Type | Sub-type (type it exactly) | Discount |
| --- | --- | --- |
| Woolen | `sweater` (lower case) | 12% |
| Woolen | `Jacket` | 8% |
| Woolen | `Blazer` | 10% |
| Festive | `Kurta Set` | 12% |
| Festive | `Sherwani` | 16% |
| Festive | `Nehru Jacket` | 8% |

GST in the calculator is a flat 10% on the pre-discount price. Any other type or sub-type stops the calculator with a `NameError`.

## Documentation

Four specifications, written from the code on this branch, are in [`Documentation/`](Documentation/):

| Document | Covers |
| --- | --- |
| [Architecture](Documentation/Architecture.pdf) | System context, program structure, data files, persistence, security, design decisions |
| [Backend Specification](Documentation/Backend%20Specification.pdf) | Data formats, sign-in, record IDs, every operation with its rules and calculations, errors and known limitations |
| [Frontend Specification](Documentation/Frontend%20Specification.pdf) | Every screen, menu, prompt and message of the console interface, and the charts |
| [Working Module](Documentation/Working%20Module.pdf) | Each module step by step with worked examples, an end-to-end walkthrough, troubleshooting |

## Notes

- Login credentials sit in plain text in `Login Id.csv`, and the password is read as an integer. Fine for a college demo, not for anything real.
- Edits are written straight back over the CSVs, so keep a copy before experimenting.
- Typing letters where the program expects a number ends it with a `ValueError`; start it again and sign in.
- Known issues, such as sales not reducing the stock quantity, are listed in the Backend Specification (section 15.3).
