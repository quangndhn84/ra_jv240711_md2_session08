package ra.run;

import ra.entity.Categories;
import ra.entity.Product;

import java.util.Scanner;

public class ShopManagement {
    public static Categories[] arrCategories = new Categories[100];
    public static int indexCategories = 0;
    public static Product[] arrProducts = new Product[100];
    public static int indexProducts = 0;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println("**************SHOP MENU*****************");
            System.out.println("1. Quản lý danh mục sản phẩm");
            System.out.println("2. Quản lý sản phẩm");
            System.out.println("3. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    displayMenuCategories(scanner);
                    break;
                case 2:
                    displayMenuProduct(scanner);
                    break;
                case 3:
                    System.exit(0);
                default:
                    System.err.println("Vui lòng chọn từ 1-3");
            }
        } while (true);
    }

    public static void displayMenuCategories(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("****************CATEGORIES MENU*****************");
            System.out.println("1. Nhập thông tin các danh mục");
            System.out.println("2. Hiển thị thông tin các danh mục");
            System.out.println("3. Cập nhật thông tin danh mục");
            System.out.println("4. Xóa danh mục");
            System.out.println("5. Cập nhật trạng thái danh mục");
            System.out.println("6. Thoát");
            System.out.print("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    inputListCategories(scanner);
                    break;
                case 2:
                    displayListCategories();
                    break;
                case 3:
                    updateCategories(scanner);
                    break;
                case 4:
                    deleteCategories(scanner);
                    break;
                case 5:
                    updateCategoriesStatus(scanner);
                    break;
                case 6:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-6");
            }
        } while (isExit);
    }

    public static void inputListCategories(Scanner scanner) {
        System.out.println("Nhâp vào số lượng danh mục cần nhập thông tin:");
        int n = Integer.parseInt(scanner.nextLine());
        for (int i = 0; i < n; i++) {
            arrCategories[indexCategories] = new Categories();
            arrCategories[indexCategories].inputData(scanner);
            indexCategories++;
        }
    }

    public static void displayListCategories() {
        System.out.println("DANH SÁCH CÁC DANH MỤC:");
        for (int i = 0; i < indexCategories; i++) {
            arrCategories[i].displayData();
        }
    }

    public static void updateCategories(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần cập nhật:");
        int catalogIdUpdate = Integer.parseInt(scanner.nextLine());
        //Kiểm tra mã danh mục có tồn tại trong arrCategories
        int indexUpdate = getIndexByCatalogId(catalogIdUpdate);
        if (indexUpdate >= 0) {
            //Tồn tại mã danh mục --> cập nhật
            boolean isExit = true;
            do {
                System.out.println("1. Cập nhập tên danh mục");
                System.out.println("2. Cập nhật mô tả danh mục");
                System.out.println("3. Câp nhật trạng thái danh mục");
                System.out.println("4. Thoát");
                System.out.print("Lựa chọn của bạn:");
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        arrCategories[indexUpdate].setCatalogName(scanner.nextLine());
                        break;
                    case 2:
                        arrCategories[indexUpdate].setDescriptions(scanner.nextLine());
                        break;
                    case 3:
                        arrCategories[indexUpdate].setCatalogStatus(Boolean.parseBoolean(scanner.nextLine()));
                        break;
                    case 4:
                        isExit = false;
                        break;
                    default:
                        System.err.println("Vui lòng chọn từ 1-4");
                }
            } while (isExit);
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public static void deleteCategories(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần xóa:");
        int catalogIdDelete = Integer.parseInt(scanner.nextLine());
        int indexDelete = getIndexByCatalogId(catalogIdDelete);
        if (indexDelete >= 0) {
            //Thực hiện xóa
            for (int i = indexDelete; i < indexCategories - 1; i++) {
                arrCategories[i] = arrCategories[i + 1];
            }
            arrCategories[indexCategories - 1] = null;
            indexCategories--;
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public static void updateCategoriesStatus(Scanner scanner) {
        System.out.println("Nhập vào mã danh mục cần cập nhật trạng thái");
        int catalogIdUpdateStatus = Integer.parseInt(scanner.nextLine());
        int indexUpdateStatus = getIndexByCatalogId(catalogIdUpdateStatus);
        if (indexUpdateStatus >= 0) {
            arrCategories[indexUpdateStatus].setCatalogStatus(!arrCategories[indexUpdateStatus].isCatalogStatus());
        } else {
            System.err.println("Mã danh mục không tồn tại");
        }
    }

    public static int getIndexByCatalogId(int catalogId) {
        for (int i = 0; i < indexCategories; i++) {
            if (arrCategories[i].getCatalogId() == catalogId) {
                return i;
            }
        }
        return -1;
    }

    public static void displayMenuProduct(Scanner scanner) {
        boolean isExit = true;
        do {
            System.out.println("**************PRODUCT MENU********************");
            System.out.println("1. Nhâp thông tin các sản phẩm");
            System.out.println("2. Hiển thị thông tin các sản phẩm");
            System.out.println("3. Sắp xếp các sản phẩm theo giá");
            System.out.println("4. Cập nhật thông tin sản phẩm theo mã sản phẩm");
            System.out.println("5. Xóa sản phẩm theo mã sản phẩm");
            System.out.println("6. Tìm kiếm sản phẩm theo tên sản phẩm");
            System.out.println("7. Tìm kiếm sản phẩm trong khoảng giá");
            System.out.println("8. Thoát");
            System.out.println("Lựa chọn của bạn:");
            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    sortProductByPriceASC();
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    searchProductByName(scanner);
                    break;
                case 7:
                    searchProductByPrice(scanner);
                    break;
                case 8:
                    isExit = false;
                    break;
                default:
                    System.err.println("Vui lòng chọn từ 1-8");
            }
        } while (isExit);
    }

    public static void sortProductByPriceASC() {
        for (int i = 0; i < indexProducts - 1; i++) {
            for (int j = i + 1; j < indexProducts; j++) {
                if (arrProducts[i].getPrice() > arrProducts[j].getPrice()) {
                    Product temp = arrProducts[i];
                    arrProducts[i] = arrProducts[j];
                    arrProducts[j] = temp;
                }
            }
        }
    }

    public static void searchProductByName(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm cần tìm:");
        String productNameSearch = scanner.nextLine();
        int cntProduct = 0;
        System.out.println("DANH SÁCH CÁC SẢN PHẦM PHÙ HỢP TIÊU CHÍ TÌM:");
        for (int i = 0; i < indexProducts; i++) {
            if (arrProducts[i].getProductName().toLowerCase().contains(productNameSearch.toLowerCase())) {
                arrProducts[i].displayData();
                cntProduct++;
            }
        }
        System.out.printf("Tìm được %d sản phẩm phù hợp\n", cntProduct);
    }

    public static void searchProductByPrice(Scanner scanner) {
        System.out.println("Nhập vào giá bắt đầu tìm kiếm:");
        float fromPrice = Float.parseFloat(scanner.nextLine());
        System.out.println("Nhập vào giá kết thúc tìm kiếm:");
        float toPrice = Float.parseFloat(scanner.nextLine());
        int cntProduct = 0;
        System.out.println("DANH SÁCH CÁC SẢN PHẨM PHÙ HỢP TIÊU CHÍ TÌM:");
        for (int i = 0; i < indexProducts; i++) {
            if (arrProducts[i].getPrice() >= fromPrice && arrProducts[i].getPrice() <= toPrice) {
                arrProducts[i].displayData();
                cntProduct++;
            }
        }
        System.out.printf("Tìm được %d sản phẩm phù hợp\n", cntProduct);
    }
}
