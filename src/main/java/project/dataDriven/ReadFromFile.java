package dataDriven;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadFromFile {
	private Workbook wb;
	private User[] users;

	public ReadFromFile() {
		try (InputStream inp = new FileInputStream("productsFile.xls")) {
			wb = WorkbookFactory.create(inp);
			users = new User[4];
			for (int i = 0; i < 4; i++) { // 4 Users
				Row rowUser = wb.getSheet("User Sheet").getRow(i);
				String username = null;
				String password = null;
				for (int j = 0; j < 2; j++) { // Username, Password
					Cell cell = rowUser.getCell(j);
					
					if (cell != null) {
						if (j == 0) {
							username = cell.getStringCellValue();
						} else
							password = cell.getStringCellValue();
					} else
						System.out.println("Cell is empty");
				}
				users[i] = new User(username,password);
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public User[] getUsers() {
		return users;
	}

	public Workbook getWb() {
		return wb;
	}
	
	public Object[][] values(String username, String password){
		Object[][] user= {{username, password}};
		return user;
	}

	public void showData() {
		for (int i = 0; i < 4; i++) { // 4 Users
			Row rowUser = wb.getSheet("User Sheet").getRow(i);
			for (int j = 0; j < 2; j++) { // Username, Password
				Cell cell = rowUser.getCell(j);
				if (cell != null) {
					if (j == 0) {
						System.out.print("User: " + cell + ", ");
					} else
						System.out.print("password: " + cell);
				} else
					System.out.println("Cell is empty");
			}
			System.out.println();
		}
	}
}