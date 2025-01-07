# ExcellHandler Author Gróf András

# Change Log: 

# 0.0.1

- Starter commit with file structure and dependencies copied to pom.xml

# 0.3.1

Domain: /api

/upload ---> Creates 2 test object into EXCEL table (GET)

/download ---> List all object from EXCEL table via JSON (GET)

/downloadName/{fileName} ---> List all object with matching filename from EXCEL table via JSON (GET)

/delete/{fileName} ---> Deletes object from EXCEL table by filename

- example : http://localhost:8080/api/downloadName/Mummy

----------------------------------------------------------------------------------------------------------------

- File structure extended with packages (Repo,Service,Model,Controller,Util)
- Model java files added (Excel, Sheet, SheetRow, SheetColumn)
- Repositories was added (-"-) and ExcellRepository findByName extension
- ExcelService added and implements repository CRUD methods (not all)
- ExcellController created with endpoints:

