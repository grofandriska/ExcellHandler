# Excel Handler 
# Author : András Gróf
 
----------------------------------------------------------------------------------------------------------------
# Change Log: 

# 0.0.1

- Starter commit with file structure and dependencies copied to pom.xml

----------------------------------------------------------------------------------------------------------------
# 0.3.1

- Domain: /api 

- /upload ---> Creates 2 test object into EXCEL table (GET)

- /download ---> List all object from EXCEL table via JSON (GET)

- /downloadName/{fileName} ---> List all object with matching filename from EXCEL table via JSON (GET)

- /delete/{fileName} ---> Deletes object from EXCEL table by filename


- File structure extended with packages (Repo,Service,Model,Controller,Util)

- Model java files added (Excel, Sheet, SheetRow, SheetColumn)

- Repositories added (-"-) and ExcelRepository findByName extension

- ExcelService added and implements repository CRUD methods (not all)

- ExcelController created with endpoints:

----------------------------------------------------------------------------------------------------------------
# 0.3.8

- ExcelRestController was refactored ,the endpoints changed to /list /delete /find
  URI ---> "/api/sheet/column"

- SheetService implementation started with Create and Read methods

- SheetColumnService implementation started with C.R.D methods 

- Converter class implementation started (convert : Optional <?>  ---> ?)

- Extended with Utilizer class (temp) to populate DB-s. 

- SheetColumnController implementation started on "/api/sheet/column"
  with endpoint: "/downloadBySheetId/{id}"


