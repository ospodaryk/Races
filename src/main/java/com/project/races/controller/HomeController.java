//package com.project.races.controller;
//
//import com.project.races.model.Pilot;
//import com.project.races.model.Race;
//import com.project.races.model.Team;
//import com.project.races.service.PilotService;
//import com.project.races.service.RaceService;
//import com.project.races.service.TeamService;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//
//@RestController
//@RequestMapping("/api")
//public class HomeController {
//    private final PilotService pilotService;
//    private final TeamService teamService;
//    private final RaceService raceService;
//    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
//
//    public HomeController(PilotService pilotService, TeamService teamService, RaceService raceService) {
//        this.pilotService = pilotService;
//        this.teamService = teamService;
//        this.raceService = raceService;
//    }
//@PostMapping
//public ResponseEntity<HttpStatus> createdddUser() {
//    return ResponseEntity.ok(HttpStatus.OK);
//}
//    @GetMapping
//    public ResponseEntity<HttpStatus> createUser() {
//        System.out.println("HERE");
//        logger.info("_______________________--HERE");
//        Race race = new Race(1L, "Haha", "My","ekjnkjew",3L,"0 51 12 31 03 *", new ArrayList<>());
//
//        Team t1 = new Team(1L, "Red Bull", 0, new ArrayList<>(), race);
//        Team t2 = new Team(2L, "Aston Martin", 0, new ArrayList<>(), race);
//        Team t3 = new Team(3L, "Mercedes", 0, new ArrayList<>(), race);
//        Team t4 = new Team(4L, "Ferrari", 0, new ArrayList<>(), race);
//
//
//        Pilot p1 = new Pilot(1L, "Alexander", "Albon", 1, 0, "Niderland", t1);
//        Pilot p2 = new Pilot(2L, "Lewis", "Hamilton", 44, 0, "United Kingdom", t3);
//        Pilot p3 = new Pilot(3L, "Fernando", "Alonso", 14, 0, "Spain", t2);
//        Pilot p4 = new Pilot(4L, "Carlos", "Sainz", 55, 0, "Spain", t4);
//
//        t1.getPilots().add(p1);
//        t2.getPilots().add(p3);
//        t3.getPilots().add(p2);
//        t4.getPilots().add(p4);
//        race.getTeams().add(t1);
//        race.getTeams().add(t2);
//        race.getTeams().add(t3);
//        race.getTeams().add(t4);
//
//        raceService.create(race);
//        teamService.create(t1);
//        teamService.create(t2);
//        teamService.create(t3);
//        teamService.create(t4);
//        pilotService.create(p1);
//        pilotService.create(p2);
//        pilotService.create(p3);
//        pilotService.create(p4);
//        System.out.println(teamService.getAll());
//        System.out.println(raceService.getAll());
//        System.out.println(pilotService.getAll());
////
////        if (bindingResult.hasErrors()) {
////            logger.error("@Post: create() has errors " + bindingResult.getAllErrors().toString());
////            returnErrorsToClient(bindingResult);
////        }
////
////        pilotService.create((userRequest));
//        return ResponseEntity.ok(HttpStatus.CREATED);
//    }
//}
