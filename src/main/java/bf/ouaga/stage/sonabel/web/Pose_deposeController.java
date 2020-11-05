/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bf.ouaga.stage.sonabel.web;

import bf.ouaga.stage.sonabel.dao.gestionScelle.DeposeRepository;
import bf.ouaga.stage.sonabel.dao.gestionScelle.PoseRepository;
import bf.ouaga.stage.sonabel.entities.gestionScelle.Depose;
import bf.ouaga.stage.sonabel.entities.gestionScelle.Pose;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Aymard
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/posedepose")
public class Pose_deposeController {
    @Autowired
    PoseRepository poseRepository;
    @Autowired
    DeposeRepository deposeRepository;
    @GetMapping("/poselist")
	public ResponseEntity<List<Pose>> poseGet() {
		List<Pose> pose= new ArrayList<>();
		try {
				poseRepository.findAll().forEach(pose::add);
			if (pose.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(pose, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
        } 
        @GetMapping("/deposelist")
	public ResponseEntity<List<Depose>> deposeGet() {
		List<Depose> depose= new ArrayList<>();
		try {
				deposeRepository.findAll().forEach(depose::add);
			if (depose.isEmpty()){
				return new ResponseEntity<>(HttpStatus.NO_CONTENT);
			}
			return new ResponseEntity<>(depose, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
        }  
}
