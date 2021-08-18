package whz.pti.swt.sponsormanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import whz.pti.swt.sponsormanagement.organizer.aggregate.EventEntity;
import whz.pti.swt.sponsormanagement.organizer.aggregate.SponsorChosenPackageEntity;
import whz.pti.swt.sponsormanagement.organizer.service.EventCommandService;
import whz.pti.swt.sponsormanagement.sponsor.aggregate.SponsorEntity;
import whz.pti.swt.sponsormanagement.sponsor.aggregate.SponsorStatus;
import whz.pti.swt.sponsormanagement.sponsor.services.SponsorService;

import java.util.ArrayList;

@SpringBootApplication
public class SponsorManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(SponsorManagementApplication.class, args);
    }


    @Autowired
    private EventCommandService eventCommandService;
    @Autowired
    private SponsorService sponsorService;

    @Bean
    CommandLineRunner runner() throws InterruptedException {
        return args -> {
            System.out.println(".....................Start Sponsor-Management.....................");

            SponsorEntity gisa= new SponsorEntity("","","","","gisa", SponsorStatus.VIP);
            SponsorEntity iplacon= new SponsorEntity("","","","","iplacon", SponsorStatus.PREMIUM);
            sponsorService.addSponsorToDb(gisa);
            sponsorService.addSponsorToDb(iplacon);
            Thread.sleep(500);
            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");


        };
    }

    private void getEventsAndParticipants() {
        ArrayList<EventEntity> allEvents = (ArrayList<EventEntity>) eventCommandService.findAllEvents();
        for (EventEntity event : allEvents) {
            System.out.println(event.getDescription() + ":: " + event.getEventId());
            if (event.getPackageChosenBySponsorList().size() > 0) {
                for (SponsorChosenPackageEntity pack : event.getPackageChosenBySponsorList()) {
                    System.out.println(pack.getFirmName() + ":: "
                            + pack.getPackageDescription());
                }
            }else{
                System.out.println(event.getDescription()+" has not any...");
            }
        }
    }
}



