    package model;


    import java.sql.ResultSet;
    import java.sql.SQLException;

    public class Product {

        private int product_id;
        private String name;
        private String category;
        private int price;

        public Product(int id, String name, String category, int price) {
            this.product_id = id;
            this.name = name;
            this.category = category;
            this.price = price;
        }

        public static Product mapRow(ResultSet rs) throws SQLException {
            int id = rs.getInt("product_id");
            String name = rs.getString("name");
            String category = rs.getString("category");
            int price = rs.getInt("price");
            // Map other attributes if needed
            return new Product(id, name, category, price);
        }

        public int getId() {
            return product_id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }



        @Override
        public String toString() {
            return "Product{" +
                    "id=" + product_id +
                    ", name='" + name + '\'' +
                    ", category=" + category +
                    ", price=" + price +
                    '}';
        }
    }
