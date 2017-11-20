package by.itacademy.keikom.simlesq;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import by.itacademy.keikom.simlesq.DAO.AdressPeopleDao;
import by.itacademy.keikom.simlesq.DAO.EducationDao;
import by.itacademy.keikom.simlesq.DAO.EducationPeopleDao;
import by.itacademy.keikom.simlesq.DAO.PeopleDao;
import by.itacademy.keikom.simlesq.DAO.WorkPeopleDao;
import by.itacademy.keikom.simlesq.entity.AdressPeople;
import by.itacademy.keikom.simlesq.entity.Education;
import by.itacademy.keikom.simlesq.entity.EducationPeople;
import by.itacademy.keikom.simlesq.entity.People;
import by.itacademy.keikom.simlesq.entity.WorkPeople;

public class Main {

	public static void main(String[] args)   {
		
		Scanner scInt = new Scanner(System.in);
		Scanner scStr = new Scanner(System.in);
		
		PeopleDao people = new PeopleDao();
		EducationDao education = new EducationDao();
		WorkPeopleDao work = new WorkPeopleDao();
		AdressPeopleDao adress = new AdressPeopleDao();
		EducationPeopleDao edPeople = new EducationPeopleDao();
		
		Integer choice = 0;
		
		do {
			
			System.out.println(">1 - посмотреть список людей");
			System.out.println(">2 - добавить нового человека");
			System.out.println(">3 - добавить новое учебное заведение");
			System.out.println("... и еще много методов, я их все тестировал....");
			System.out.println(">0 - выход");
			System.out.print("> ");
			choice = scInt.nextInt();
			
			switch (choice) {
			case 1:{
					List<People> list = people.allRecords();
					for(People i : list) {
						System.out.println(i);
					}
					System.out.println();
					break;
				}
			case 2:{
					People newpeople = new People();
				
					System.out.print("Введите имя: ");
					newpeople.setP_name(scStr.nextLine());
					System.out.print("Введите возраст: ");
					newpeople.setP_age(scInt.nextInt());
					newpeople.setP_org(1);
					newpeople.setP_id(1);
					people.create(newpeople);
					break;
				}
			case 3:{
					Education neweducation = new Education();
					System.out.print("Введите название: ");
					neweducation.setInst_name(scStr.nextLine());
					System.out.print("Введите адрес: ");
					neweducation.setInst_adress(scStr.nextLine());
					System.out.print("Коментарий: ");
					neweducation.setComment(scStr.nextLine());
					education.create(neweducation);
					break;
				}
			}
			
		}while (choice != 0);
		
	}
}
