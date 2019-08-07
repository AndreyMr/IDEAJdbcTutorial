import dao.daointerfaceimpl.AddressDAOImpl;
import entity.Address;

public class Start {
    public static void main(String[] args) {
        Address address1 = new Address();
        address1.setCountry("America");
        address1.setStreet("Long Avenu");
        address1.setCity("Washington");
        address1.setPostCode(556889);
        address1.setId(2);


        AddressDAOImpl addressDAO = new AddressDAOImpl();
        addressDAO.add(address1);
        System.out.println("============= Получаем полный список ================");
            //получаем полный список
            for (Address address : addressDAO.getAll()
                    ) {
                System.out.println(address);
            }
            System.out.println("=============================");
            // UPDATE
            address1.setCountry("France");
            addressDAO.update(address1);
            System.out.println("=============================");
            //
            Address address = addressDAO.getById(2);

            System.out.println("=============================");

            addressDAO.remove(address1);
            System.out.println("=============================");

            for (Address adr : addressDAO.getAll()
                ) {
            System.out.println(adr);
            }



    }
}
