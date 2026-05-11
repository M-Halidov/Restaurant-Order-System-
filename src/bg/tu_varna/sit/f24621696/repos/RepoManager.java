package bg.tu_varna.sit.f24621696.repos;

import bg.tu_varna.sit.f24621696.exceptions.CommandException;
import bg.tu_varna.sit.f24621696.exceptions.FileException;

import java.io.*;

/**
 * The manager class of all the repositories.
 */
public class RepoManager implements Serializable {
    /**
     * Currently stored file.
     */
    private File file;
    /**
     * The menu item repository.
     */
    private MenuItemRepo menuItemRepo;
    /**
     * The table repository.
     */
    private TableRepo tableRepo;
    /**
     * The order repository.
     */
    private OrderRepo orderRepo;


    /**
     * Constructs RepoManager and initializes all the repositories.
     */
    public RepoManager() {
        this.tableRepo = new TableRepo();
        this.menuItemRepo = new MenuItemRepo();
        this.orderRepo = new OrderRepo();
    }

    /**
     * Returns the menu item repository.
     * @return The repository containing all menu items.
     */
    public MenuItemRepo getMenuItemRepo() {
        return menuItemRepo;
    }

    /**
     * Returns the order repository.
     * @return The repository containing all orders.
     */
    public OrderRepo getOrderRepo() {
        return orderRepo;
    }

    /**
     * Returns the table repository.
     * @return The repository containing all orders.
     */
    public TableRepo getTableRepo() {
        return tableRepo;
    }


    /**
     * Returns the currently open file.
     * @return The currently open file.
     */
    public File getFile() {
        return file;
    }

    /**
     * Opens the passed file and loads all data from it to the repositories.
     * If the file isn't created it creates a new one.
     * If the file is empty it function returns early.
     * Clears all data from the repositories before loading.
     * @param file The file to open and read data from.
     * @throws FileException If it failed to create the file.
     * @throws FileException If the file could not be read.
     */
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

    /***
     * Serializes and writes all repository data into the given file.
     * If the given file is different from the currently stored file in manager.
     * The file gets reassigned to the newly passed one.
     * @param file The file to write to.
     * @throws FileException If no file is currently open.
     * @throws FileException If it failed to write to the file.
     */
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
            throw new FileException("Failed to write data into file!");
        }
    }

    /**
     * Closes the currently open file and reinstates all repositories.
     */
    public void close() {
        file = null;
        menuItemRepo = new MenuItemRepo();
        tableRepo = new TableRepo();
        orderRepo = new OrderRepo();

    }

    /**
     * Checks whether a file is currently open.
     * @return True if a file is open or false if one isn't open
     */
    public boolean isFileOpen() {
        return (file !=null);
    }
}
