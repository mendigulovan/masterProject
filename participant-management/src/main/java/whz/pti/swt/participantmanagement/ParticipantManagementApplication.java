package whz.pti.swt.participantmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import whz.pti.swt.participantmanagement.organizer.aggregate.EventEntity;
import whz.pti.swt.participantmanagement.organizer.service.HackathonEventService;
import whz.pti.swt.participantmanagement.participant.aggregate.ParticipantDataEntity;
import whz.pti.swt.participantmanagement.participant.aggregate.ParticipantEntity;
import whz.pti.swt.participantmanagement.participant.services.ParticipantService;

import java.util.ArrayList;

@SpringBootApplication
public class ParticipantManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(ParticipantManagementApplication.class, args);
    }

    @Autowired
    private ParticipantService participantService;

    @Autowired
    private HackathonEventService eventService;

    @Bean
    CommandLineRunner runner() throws InterruptedException {
        return args -> {
            System.out.println(".....................Start Participant-Management.....................");

            ParticipantEntity st1 = new ParticipantEntity("", "", "Alina Bolotbekova");
            ParticipantEntity st2 = new ParticipantEntity("", "", "Begaiym Asanbekova");
            ParticipantEntity st3 = new ParticipantEntity("", "", "Nagima Mendigulova");
            ParticipantEntity st4 = new ParticipantEntity("", "", "Sezimai Zholboldueva");
            participantService.addParticipant(st1).get();
            Thread.sleep(5000);

            participantService.addParticipant(st2).get();
            Thread.sleep(5000);

            participantService.addParticipant(st3).get();
            Thread.sleep(5000);

            participantService.addParticipant(st4).get();
            Thread.sleep(5000);

            System.out.println("::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::");

            getEventsAndParticipants();

        };
    }

    private void getEventsAndParticipants() {
        ArrayList<EventEntity> allEvents = (ArrayList<EventEntity>) eventService.findAllEvents();
        for (EventEntity event : allEvents) {
            System.out.println(event.getDescription() + ":: " + event.getEventId());
            if (event.getParticipantsOfEvent().size() > 0) {
                for (ParticipantDataEntity part : event.getParticipantsOfEvent()) {
                    System.out.println("Name of Participant: "+ part.getName() + "::Food Wish: "
                            + part.getFoodWishes() + " ::Technology: " + part.getTechnologie() + ":: T-Shirt size: " + part.getTShirtSize());
                }
            }else{
                System.out.println(event.getDescription()+" Participant can't participate in this Event");
            }
        }
    }
}
