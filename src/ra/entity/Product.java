package ra.entity;

import com.sun.security.jgss.GSSUtil;
import ra.run.ShopManagement;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Product {
    private String productId;
    private String productName;
    private float price;
    private String descriptions;
    private Date created;
    private int catalogId;
    private int productStatus;

    public Product() {
    }

    public Product(String productId, String productName, float price, String descriptions, Date created, int catalogId, int productStatus) {
        this.productId = productId;
        this.productName = productName;
        this.price = price;
        this.descriptions = descriptions;
        this.created = created;
        this.catalogId = catalogId;
        this.productStatus = productStatus;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public int getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(int productStatus) {
        this.productStatus = productStatus;
    }

    public void inputData(Scanner scanner) {
        this.productId = inputProductId(scanner);
        this.productName = inputProductName(scanner);
        this.price = inputPrice(scanner);
        this.descriptions = inputDescriptions(scanner);
        this.created = inputCreated(scanner);
        this.catalogId = inputCatalogId(scanner);
        this.productStatus = inputProductStatus(scanner);
    }

    public String inputProductId(Scanner scanner) {
        System.out.println("Nhập vào mã sản phẩm:");
        String productIdRegex = "[C|S|A]\\w{3}";
        do {
            String productId = scanner.nextLine();
            if (Pattern.matches(productIdRegex, productId)) {
                //Kiểm tra trùng lặp
                boolean isExist = false;
                for (int i = 0; i < ShopManagement.indexProducts; i++) {
                    if (ShopManagement.arrProducts[i].getProductId().equals(productId)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Mã sản phẩm đã tồn tại, vui lòng nhập lại");
                } else {
                    return productId;
                }
            } else {
                System.err.println("Không đúng định dạng, vui lòng nhập lại");
            }
        } while (true);
    }

    public String inputProductName(Scanner scanner) {
        System.out.println("Nhập vào tên sản phẩm:");
        String productNameRegex = "\\w{10,50}";
        do {
            String productName = scanner.nextLine();
            if (Pattern.matches(productNameRegex, productName)) {
                //Kiểm tra tồn tại
                boolean isExist = false;
                for (int i = 0; i < ShopManagement.indexProducts; i++) {
                    if (ShopManagement.arrProducts[i].getProductName().equals(productName)) {
                        isExist = true;
                        break;
                    }
                }
                if (isExist) {
                    System.err.println("Tên sản phẩm đã tồn tại, vui lòng nhập lại");
                } else {
                    return productName;
                }
            } else {
                System.err.println("Tên sản phẩm có từ 10-50 ký tự, vui lòng nhập lại");
            }
        } while (true);
    }

    public float inputPrice(Scanner scanner) {
        System.out.println("Nhập vào giá sản phẩm:");
        do {
            float price = Float.parseFloat(scanner.nextLine());
            if (price > 0) {
                return price;
            }
            System.err.println("Giá sản phẩm phải lớn hơn 0, vui lòng nhập lại");
        } while (true);
    }

    public String inputDescriptions(Scanner scanner) {
        System.out.println("Nhập vào mô tả sản phẩm:");
        return scanner.nextLine();
    }

    public Date inputCreated(Scanner scanner) {
        System.out.println("Nhập vào ngày nhập của sản phẩm:");
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        do {
            try {
                Date created = sdf.parse(scanner.nextLine());
                return created;
            } catch (Exception ex) {
                System.err.println("Không đúng định dạng ngày, vui lòng nhập lại");
            }
        } while (true);
    }

    public int inputCatalogId(Scanner scanner) {
        System.out.println("Chọn danh mục mà sản phẩm thuộc về:");
        for (int i = 0; i < ShopManagement.indexCategories; i++) {
            System.out.printf("%d.%s\n", (i + 1), ShopManagement.arrCategories[i].getCatalogName());
        }
        System.out.print("Lựa chọn của bạn:");
        int choice = Integer.parseInt(scanner.nextLine());
        return ShopManagement.arrCategories[choice - 1].getCatalogId();
    }

    public int inputProductStatus(Scanner scanner) {
        System.out.println("Chọn trạng thái sản phẩm:");
        System.out.println("1. Đang bán");
        System.out.println("2. Hết hàng");
        System.out.println("3. Không bán");
        System.out.print("Lựa chọn của bạn: ");
        return Integer.parseInt(scanner.nextLine()) - 1;
    }

    public void displayData() {
        System.out.printf("Mã sản phẩm: %s - Tên sản phẩm: %s - Giá: %f\n", this.productId, this.productName, this.price);
        System.out.printf("Ngày tạo: %s - Tên danh mục: %s - Trạng thái: %s\n",
                this.created, getCatalogNameById(this.catalogId), this.productStatus == 0 ? "Đang bán" : (this.productStatus == 1 ? "Hết hàng" : "Không bán"));
        System.out.printf("Mô tả: %s\n", this.descriptions);
    }

    public String getCatalogNameById(int catalogId) {
        for (int i = 0; i < ShopManagement.indexCategories; i++) {
            if (ShopManagement.arrCategories[i].getCatalogId() == catalogId) {
                return ShopManagement.arrCategories[i].getCatalogName();
            }
        }
        return "";
    }

}
