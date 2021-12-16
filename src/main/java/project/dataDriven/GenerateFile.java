package dataDriven;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import org.apache.poi.ss.usermodel.Sheet;

import org.apache.poi.ss.usermodel.Workbook;

public class GenerateFile {
	public static void main(String[] args) throws FileNotFoundException, IOException {
		Workbook wb = new HSSFWorkbook();
		try (OutputStream fileOut = new FileOutputStream("productsFile.xls")) {
			Sheet sheetUsers = wb.createSheet("User Sheet");
			ArrayList<User> users = new ArrayList<>();
			addUser(users, "Dvir", "1234");
			addUser(users, "Amiti", "2345");
			addUser(users, "alfa", "1234567");
			addUser(users, "betaa", "234589");
			for (int i = 0; i < 4; i++) { // 4 Users
				Row rowUser = sheetUsers.createRow(i);
				for (int j = 0; j < 2; j++) { // UserName, PassWord
					Cell cell = rowUser.createCell(j);
					if (j==0)
						cell.setCellValue(users.get(i).getUsername());
					else
						cell.setCellValue(users.get(i).getPassword());
				}
			}
//			Sheet sheetProducts = wb.createSheet("Product Sheet");
			wb.write(fileOut);
			wb.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

	public static void addUser(ArrayList<User> array, String userName, String password) {
		User user = new User(userName, password);
		array.add(user);
	}
}
