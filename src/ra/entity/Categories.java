package ra.entity;

import ra.run.ShopManagement;

import java.util.Scanner;

public class Categories {
    private int catalogId;
    private String catalogName;
    private String descriptions;
    private boolean catalogStatus;

    public Categories() {
    }

    public Categories(int catalogId, String catalogName, String descriptions, boolean catalogStatus) {
        this.catalogId = catalogId;
        this.catalogName = catalogName;
        this.descriptions = descriptions;
        this.catalogStatus = catalogStatus;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public boolean isCatalogStatus() {
        return catalogStatus;
    }

    public void setCatalogStatus(boolean catalogStatus) {
        this.catalogStatus = catalogStatus;
    }

    public void inputData(Scanner scanner) {
        this.catalogId = inputCatalogId();
        this.catalogName = inputCatalogName(scanner);
        this.descriptions = inputDescription(scanner);
        this.catalogStatus = inputCatalogStatus(scanner);
    }

    public int inputCatalogId() {
        if (ShopManagement.indexCategories == 0) {
            //Mảng arrCategories chưa có danh mục
            return 1;
        } else {
            //Có chứa danh mục --> tìm mã danh mục có giá trị lớn nhất trong mảng
            int maxId = ShopManagement.arrCategories[0].getCatalogId();
            for (int i = 1; i < ShopManagement.indexCategories; i++) {
                if (maxId < ShopManagement.arrCategories[i].getCatalogId()) {
                    maxId = ShopManagement.arrCategories[i].getCatalogId();
                }
            }
            return maxId + 1;
        }
    }

    public String inputCatalogName(Scanner scanner) {
        System.out.println("Nhập vào tên danh mục:");
        do {
            String catalogName = scanner.nextLine();
            if (catalogName.length() > 50) {
                System.err.println("Tên danh mục tối đa 50 ký tự, vui lòng nhập lại");
            } else {
                //Duy nhất
                boolean isExist = false;
                for (int i = 0; i < ShopManagement.indexCategories; i++) {
                    if (ShopManagement.arrCategories[i].getCatalogName().equals(catalogName)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên danh mục đã tồn tại, vui lòng nhập lại");
                } else {
                    return catalogName;
                }
            }
        } while (true);
    }

    public String inputDescription(Scanner scanner) {
        System.out.println("Nhập vào mô tả danh mục:");
        return scanner.nextLine();
    }

    public boolean inputCatalogStatus(Scanner scanner) {
        System.out.println("Nhập vào trạng thái danh mục:");
        do {
            String status = scanner.nextLine();
            if (status.equals("true") || status.equals("false")) {
                return Boolean.parseBoolean(status);
            } else {
                System.err.println("Trạng thái danh mục chỉ nhận giá trị true hoặc false, vui lòng nhập lại");
            }
        } while (true);
    }

    public void displayData() {
        System.out.printf("Mã danh mục: %d - Tên danh mục: %s - Mô tả: %s - Trạng thái: %s\n",
                this.catalogId, this.catalogName, this.descriptions, this.catalogStatus ? "Hoạt động" : "Không hoạt động");
    }


}
