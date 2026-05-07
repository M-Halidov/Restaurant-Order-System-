package bg.tu_varna.sit.f24621696.repos;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.exceptions.FileException;

import java.io.*;

public class RepoManager implements Serializable{
    private File file;
    private MenuItemRepo menuItemRepo;
    private TableRepo tableRepo;
    private OrderRepo orderRepo;

    public RepoManager() {
        this.tableRepo = new TableRepo();
        this.menuItemRepo = new MenuItemRepo();
        this.orderRepo = new OrderRepo();
    }

    public MenuItemRepo getMenuItemRepo() {
        return menuItemRepo;
    }

    public OrderRepo getOrderRepo() {
        return orderRepo;
    }

    public TableRepo getTableRepo() {
        return tableRepo;
    }

    public File getFile() {
        return file;
    }

    public void open(File file) {
        this.file = file;
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                this.file = null;
                throw new FileException("Failed to open file " + file.getName() + ", Error while finding directory!");
            }
            return;
        }

        menuItemRepo.clearItems();
        tableRepo.getItems().clear();
        orderRepo.getItems().clear();

        if (file.length()==0) return;

        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            MenuItemRepo tempItemRepo = (MenuItemRepo) ois.readObject();
            menuItemRepo.setItems(tempItemRepo.getItems());

            TableRepo tempTableRepo = (TableRepo) ois.readObject();
            tableRepo.setItems(tempTableRepo.getItems());

            OrderRepo tempOrderRepo = (OrderRepo) ois.readObject();
            orderRepo.setItems(tempOrderRepo.getItems());

            ois.close();
        } catch (IOException | ClassNotFoundException e) {
            throw new FileException("Failed to read file " + file.getName());
        }

    }

    public void write(File file) {
        if (file == null) throw new FileException("Please open a file!");
        if (file != this.file) this.file = file;

        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(menuItemRepo);
            oos.writeObject(tableRepo);
            oos.writeObject(orderRepo);
            oos.close();
        } catch (IOException e) {
            throw new FileException(e.getMessage());
        }
    }

    public void close() {
        file = null;
        menuItemRepo = new MenuItemRepo();
        tableRepo = new TableRepo();
        orderRepo = new OrderRepo();

    }

    public boolean isFileOpen() {
        return (file !=null);
    }
}
