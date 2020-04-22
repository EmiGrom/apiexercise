package apiproject;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MainApp implements Runnable {

    /*
    Zadanie:
    1. Wykonać link w przeglądarce: http://dummy.restapiexample.com/api/v1/employees
    2. Otworzyć nowy projekt Mavenowy
    3. Skonfigurować oraz wrzucić na gita
    4. Napisać parser JSON-a który zrzuci listę pracowników, wyświetli ich w konsoli oraz wrzucić kommit ze zmianami na gita
    5. Utworzyć liste obiektów które zwraca powyższe api
    6. Utworzyć metody, które przerobią liste oraz wyświetlić pracowników których wiek jest większy niż 30
    7. Utworzyć metody, które przerobią liste oraz wyświetlić pracowników posortować rosnąco po pensji
    8. Utworzyć metody, które przerobią liste oraz wyświetlić pracowników posortować malejąco po wieku
    */

    @Override
    public void run() {
        try {
            String response = new HTTPService().connect(Config.APP_URL);
            parseJson(response);
            over30(response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<Employee> parseJson(String json) {
        JSONObject jsonObject = new JSONObject(json);
        JSONArray jsonArrayEmployees = jsonObject.getJSONArray("data");

        List<Employee> employeesList = new ArrayList<>();

        for (int i = 0; i < jsonArrayEmployees.length(); i++) {
            JSONObject one = (JSONObject) jsonArrayEmployees.get(i);
            Employee employee = new Employee();
            employee.setId(Integer.parseInt(one.get("id").toString()));
            employee.setAge(Double.parseDouble(one.get("employee_age").toString()));
            employee.setName(one.get("employee_name").toString());
            employee.setSalary(Double.parseDouble(one.get("employee_salary").toString()));
            employeesList.add(employee);
        }

        System.out.println("Logs: ");
        System.out.println(employeesList);
        System.out.println(employeesList.size());
        System.out.println(jsonArrayEmployees.length());

        return employeesList;
    }

    private List<Employee> over30(String json) {
        JSONObject over30 = new JSONObject(json);
        JSONArray jsonArrayEmployeesOver30 = over30.getJSONArray("data");

        List<Employee> employeesList2 = new ArrayList<>();

        for (int i = 0; i < jsonArrayEmployeesOver30.length(); i++) {
            JSONObject overThirty = (JSONObject) jsonArrayEmployeesOver30.get(i);
            Employee employee = new Employee();
            employee.setId(Integer.parseInt(overThirty.get("id").toString()));
            employee.setAge(Double.parseDouble(overThirty.get("employee_age").toString()));
            employee.setName(overThirty.get("employee_name").toString());
            employee.setSalary(Double.parseDouble(overThirty.get("employee_salary").toString()));
            if (employee.getAge() > 30) {
                employeesList2.add(employee);
            }

        }
        System.out.println("Logs: ");
        System.out.println(employeesList2);;
        System.out.println(employeesList2.size());
        System.out.println(jsonArrayEmployeesOver30.length());
        return employeesList2;

    }
}