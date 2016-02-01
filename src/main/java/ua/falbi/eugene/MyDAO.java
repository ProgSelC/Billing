package ua.falbi.eugene;

import java.util.List;


public interface MyDAO
{
	List<Client> list();
    List<Client> list(String pattern);
    // Список удаленных клиентов
    List<Client> trashList();

    Client clientById(long id);

    // История платежей
    List<Payment> payLog(long id);

	void add(Client cli);

    void delete(long id);
    void delete(long[] ids);

    void remove(long id);
    void remove(long[] ids);

    void restore(long id);
    void restore(long[] ids);

    void depositMoney(long id, double sum);
    void writeoffMoney(long id);
    void writeoffSelectedMoney(long[] ids);

    byte[] getLogo(long id);
}
