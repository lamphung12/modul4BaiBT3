package service;

import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductService implements IProductService{
    List<Product> products = new ArrayList<>();

    public ProductService() {
        products = new ArrayList<>();
        products.add(new Product(1,"May tinh",200,"aaa"));
        products.add(new Product(2,"May giat",250,"aba"));
        products.add(new Product(3,"Quat dien",200,"baa"));
        products.add(new Product(4,"Dien Thoai",400,"cca"));
        products.add(new Product(5,"Ban Ghe",350,"abc"));

    }



    @Override
    public List<Product> findAll() {

        return products;
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public Product findById(int id) {
        for (Product product:products) {
            if (product.getId()==id){
                return product;
            }
        }
        return null;
    }

    @Override
    public int indexOfById(int id) {
        int index =-1;
        for (int i = 0; i < products.size() ; i++) {
            if(products.get(i).getId()==id){
                index=i;
            }
        }
        return 0;
    }

    @Override
    public void update(int id, Product product) {
        int indexOf = indexOfById(id);
        products.set(indexOf,product);
    }

    @Override
    public void remove(int id) {
        int indexOfDelete =indexOfById(id);
        products.remove(indexOfDelete);
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> list = new ArrayList<>();
        for (int i = 0; i < products.size() ; i++) {
            if(products.get(i).getName().contains(name)){
               list.add(products.get(i));
            }
        }
        return list;
    }
}
