import model.Person;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;
import java.util.List;


public class Consumer {

    private static final String URL = "http://94.198.50.185:7081/api/users";
    private static String sessionId;
    private static StringBuilder code;

    public static void main(String[] args) throws URISyntaxException {

        //создаем шаблон запроса
        RestTemplate restTemplate = new RestTemplate();

        // отправляем GET запрос и в ответ получаем сущность в виде списка объектов Person
        ResponseEntity<List<Person>> response = restTemplate.exchange(
                URL, // URL
                HttpMethod.GET, // Метод запроса
                null, // Тело запроса (null, так как GET)
                new ParameterizedTypeReference<List<Person>>() {}
                // Тип ответа
                // Создаем анонимный класс, наследованный от абстрактного класса
                // ParameterizedTypeReference, который будет хранить тип ответа после комиляции
                // Мы бы могли сделать проще вернув просто массив массив Person []
                // ResponseEntity<Person[]> response = restTemplate.exchange(URL, HttpMethod.GET, null, Person[].class)
                // преобразовав его в List<Person>  listPerson = Arrays.asList(Person[])
                // надеюсь прочитав этот комент в будущем, я вспомню что здесь имел ввиду? :-)
        );

        // сохраним Cookie который мы получили при первом GET запросе, который хранится в заголовке ответа от сервера
        sessionId = response.getHeaders().getFirst("Set-Cookie");

        for (Person person : response.getBody()) {
            System.out.println(person);
        }  //вывели полученный список, который хранится в теле ответа на наш заврос

        // создадим нового человека
        Person personToAdd = new Person(3, "James", "Brown", 22);

        // создадим заголовок для нового запроса, и вложим в него тип контента и сессию
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Cookie", sessionId);

        // сделаем POST запрос на создание пользователя используя Cookie из предыдущего запроса
        // условие задачи использовать Cookie полученный нами при первом обращении к серверу
        // в дальнейшем мы будем использовать только этот заголовок
        HttpEntity<Person> request = new HttpEntity<>(personToAdd, headers);
        ResponseEntity<String> createPerson = restTemplate.exchange(
                URL,
                HttpMethod.POST,
                request,
                String.class);

        code = new StringBuilder();
        //добавляем первую часть кода в нащ StringBuilder code
        code.append(createPerson.getBody());

        personToAdd.setName("Thomas");
        personToAdd.setLastName("Shelby");

        request = new HttpEntity<>(personToAdd, headers);

        // изменяем на сервере человека с id=3
        ResponseEntity<String> changedPerson = restTemplate.exchange(URL, HttpMethod.PUT, request, String.class);

        //добавляем вторую часть кода
        code.append(changedPerson.getBody());

        //удаляем человека с id 3
        ResponseEntity<String> deletePerson = restTemplate.exchange(URL + "//" + personToAdd.getId(), HttpMethod.DELETE, request, String.class);

        //добавляем третью часть кода которую получаем из сушности которую вернул запрос удаления
        code.append(deletePerson.getBody());

        //выводим код полученный с сервера
        System.out.println("код полученный с сервера: " + code.toString());

    }

}
