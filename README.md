#                                              Excel Handler 
#                                          Author : András Gróf
 
----------------------------------------------------------------------------------------------------------------
                                             # Change Log: 

----------------------------------------------------------------------------------------------------------------

# 0.7.5

- Domain localhost:8080/api/excel/index

- **Refactors**: boilerplate and unused classes and codes were removed (Converter class, Controller classes,Service class and methods...)

- **ExcelFileReader** util class created with and businessLogic(DB Save) moved into saveDataFromExcelToDatabases() {}
  where: '**Excel**' - '**Sheet**' - '**SheetColumn**' - '**SheetRow**' ~ '**service**' methods called (save)
 
- **ExelService** was extended: createExcelAndSave (String pathName) {} -> calls repository**.save()**

- **CellInspector** util class created for reading and getting each cell's type and value

- **Minor SheetColumnService change** : createAndSave (Sheet sheet, Sheet sheetColumnSheet){}
    



# 0.3.8

- ExcelRestController was refactored ,the endpoints changed to /list /delete /find
  URI ---> "/api/sheet/column"

- SheetService implementation started with Create and Read methods

- SheetColumnService implementation started with C.R.D methods

- Converter class implementation started (convert : Optional <?>  ---> ?)

- Extended with Util class (temp) to populate DBs.

- SheetColumnController implementation started on "/api/sheet/column"
  with endpoint: "/downloadBySheetId/{id}"

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
# 0.0.1

- Starter commit with file structure and dependencies copied to pom.xml






