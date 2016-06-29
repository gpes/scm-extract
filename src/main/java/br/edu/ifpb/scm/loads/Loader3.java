/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.scm.loads;

import br.edu.ifpb.scm.api.Repository;
import br.edu.ifpb.scm.api.ScmFactory;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import org.eclipse.jgit.api.errors.GitAPIException;

/**
 *
 * @author Anderson Souza
 */
public class Loader3 {

    public static String URL = "https://github.com/EndenhariaDeSoftware/scm-extract";
    public static File PATH = new File("C:/Users/Amanda/Documents/scm");
    public static File PATH3 = new File("C:/Users/Amanda/Documents/scm2");
    public static File PATH2 = new File("C:/Users/Pris/Desktop/scm");
//    public static File PATH3 = new File("/Users/job/Documents/dev/data/scm");
    public static File origin = new File("/Users/job/Documents/dev/data/scm");
    public static File dest = new File("/Users/job/Documents/dev/data/scm2");

    public static void main(String[] args) throws GitAPIException, IOException, ParseException {
        Repository repository = ScmFactory.create("git").clone(URL, PATH);
        print(repository);
    }

    public static void print(Repository repository) {
        System.out.println("URL LOCAL: " + repository.pathLocal());
        System.out.println("URL REMOTE: " + repository.urlRemote());
//        System.out.println(repository.getVersions());
    }

    public static void main2(String[] args) throws ParseException {
        DateTimeFormatter dateTimeFormatter1 = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss z");
        DateTimeFormatter dateTimeFormatter2 = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        DateTimeFormatter dateTimeFormatter3 = DateTimeFormatter.ofPattern("dd/MMM/YYYY");
        DateTimeFormatter formatterWithZone = dateTimeFormatter3.withZone(ZoneId.of("America/Los_Angeles"));

        ZoneId getZoneMethod = formatterWithZone.getZone();
        LocalTime time = LocalTime.parse("10:15:30", DateTimeFormatter.ISO_TIME);
        LocalDate date = LocalDate.parse("2015-04-10", DateTimeFormatter.ISO_DATE);
        ZonedDateTime zonedDateTime = ZonedDateTime.now();
        String formatter1 = dateTimeFormatter1.format(zonedDateTime);
        String formatter2 = dateTimeFormatter2.format(zonedDateTime);
        String formatter3 = dateTimeFormatter3.format(zonedDateTime);
        String formatterWithZoneStr = formatterWithZone.format(zonedDateTime);
        System.out.println("DateTimeFormatter Example Demo");
        System.out.println("------------------------------");
        System.out.println("DateTimeFormatter 1 : " + formatter1);
        System.out.println("DateTimeFormatter 2 : " + formatter2);
        System.out.println("DateTimeFormatter 3 : " + formatter3);
        System.out.println("DateTimeFormatter with Zone3 : " + formatterWithZoneStr);
        System.out.println("DateTimeFormatter().getZone() : " + getZoneMethod.getId());
        System.out.println("LocalTime.parser - DateTimeFormatter.ISO_TIME : "
                + time);
        System.out.println("LocalDate.parser - DateTimeFormatter.ISO_TIME : "
                + date);
    }
}
