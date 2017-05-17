package com.zyc;

import com.zyc.java8.filter.ObjectFilter;
import com.zyc.java8.po.Person;
import com.zyc.java8.tactics.PersonFilter;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//@RunWith(SpringRunner.class)
//@SpringBootTest
public class TestForJava8ApplicationTests {

	private List<Person> list = Arrays.asList(new Person(1,"laosan",18,new BigDecimal(300)),
			new Person(2,"laosi",19,new BigDecimal(400)),
			new Person(3,"laowu",20,new BigDecimal(500)),
			new Person(4,"laoliu",22,new BigDecimal(600)));


    @Test
    public void test10(){

    }
	@Test
	public void contextLoads() {
		list.forEach((Person p)-> System.out.println(p));

	}

	@Test
	public void test1(){
        List<Person> persons = filterPerson(list, new PersonFilter() {
            @Override
            public boolean filterPersonByAge(Person person) {
                return person.getAge() >= 19;
            }
        });

        persons.forEach(System.out::println);
    }

    @Test
    public void testLamda(){
        List<Person> persons = filterPerson(list, (person) -> person.getAge() >= 19);
        persons.forEach(System.out::println);

        PersonFilter pf = (person) -> person.getAge()>10;

    }

	public List<Person> filterPerson(List<Person> list1,PersonFilter filter){
		List<Person> newList = new ArrayList<>();
		for (Person person:list1){
			if(filter.filterPersonByAge(person)){
				newList.add(person);
			}
		}
		return newList;
	}

	public List filterObject(List<Integer> list, ObjectFilter<Integer> objectFilter){
        List result = new ArrayList<>();
        for (Object obj:list){
            if(objectFilter.filter((Integer)obj)){
                result.add(obj);
            }
        }

        return result;
    }

	@Test
    public void test(){
        List<Integer> list = Arrays.asList(1,2,3,4,6,5,10,22,7);

        this.filterObject(list,(Integer i)-> i % 1 == 0).forEach(System.out::println);

    }






}
