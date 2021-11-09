package com.bezkoder.springjwt.controllers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.bezkoder.springjwt.exceptions.SceneNotFoundException;
import com.bezkoder.springjwt.models.SceneModel;
import com.bezkoder.springjwt.repository.SceneRepository;

@CrossOrigin(origins = "*", maxAge = 3600)

@RestController
@RequestMapping("/scene")
public class SceneController {

	@Autowired
	SceneRepository sceneRepository;

	@Autowired
	EntityManagerFactory emf;

//	@GetMapping("/get/all")
//	List<SceneModel> all(){
//		return sceneRepository.findAll();
//	}
	private static final Logger logger = LoggerFactory.getLogger(SceneController.class);
	//Only scene Data
	@GetMapping("/get/{sUserId}/all")
	List<SceneModel> getAllScenesByUser(@PathVariable String sUserId) {
//		long uid = Long.parseLong(sUserId);
		// Just for testing the logger!
		logger.info("User ID for SceneController: " + sUserId);
		return sceneRepository.findByUserId(sUserId);
	}
	
	//Get All scene with data from other tables
	@GetMapping("/get/{sUserId}/allscenes")
	List<Object[]> getAllScenesDataByUser(@PathVariable String sUserId) {
		EntityManager em = emf.createEntityManager();
		Query query = em.createQuery(
				"select s.id,s.userId, s.name, img.id,img.image_name, img.imageUrl, v.video_path from SceneModel s "
						+ " join ImageModel img on s.imageId = img.id " + " join VideoModel v on img.id = v.imageId "
						+ " join MarkerModel m on img.id = m.imageId " + "where s.userId like '" + sUserId+"'");
		logger.info("Native query ");

		@SuppressWarnings("unchecked")
		List<Object[]> scenes = (List<Object[]>) query.getResultList();
		em.close();
		logger.info("Get User: "+sUserId+" All Scenes -> " +scenes.size());


		return scenes;
	}
	
	
	//Renders All scenes
	@GetMapping("/get/{sUserId}/data")
	@ResponseBody //->Reponse to Json instead of Array of arrays
	List<Object[]> getAllScenesDataByUser(
			@PathVariable String sUserId,
			@RequestParam("sceneId") String sceneId) {
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery(
				"select s.id,s.userId, s.name, img.id,img.image_name,v.video_path, m.markerUrl from SceneModel s "
						+ " join ImageModel img on s.imageId = img.id " + " join VideoModel v on img.id = v.imageId "
						+ " join MarkerModel m on img.id = m.imageId " + "where s.userId like '" + sUserId+"'");
		logger.info("Native query: ");

		@SuppressWarnings("unchecked")
		List<Object[]> scenes = (List<Object[]>) query.getResultList();
		em.close();

		return scenes;
	}
	
	//Render specific Scene
	@GetMapping("/get/{sUserId}/{sceneId}/data")
	@ResponseBody //->Reponse to Json instead of Array of arrays
	List<Object[]> getThatScenesDataByUser(
			@PathVariable String sUserId,
			@PathVariable String sceneId){
		EntityManager em = emf.createEntityManager();
		
		Query query = em.createQuery(
				"select s.id,s.userId, s.name, img.id,img.image_name, img.imageUrl, v.video_path, m.markerUrl from SceneModel s "
						+ " join ImageModel img on s.imageId = img.id " + " join VideoModel v on img.id = v.imageId "
						+ " join MarkerModel m on img.id = m.imageId " + "where s.userId like '" + sUserId+"' and s.id ="+sceneId);
		logger.info("Native query: ");

		@SuppressWarnings("unchecked")
		List<Object[]> scenes = (List<Object[]>) query.getResultList();
		em.close();

		return scenes;
	}

	@PostMapping("/add")
	SceneModel newScene(@RequestBody SceneModel newScene) {
		SceneModel scene = new SceneModel(newScene.getName(), newScene.getTags(), newScene.getImageId(),
				newScene.getUserId(), newScene.getImageName(), newScene.getOverlays(), newScene.getCreationDate());
		logger.info("SCENE INFOS: " + newScene.getUserId() + " " + newScene.getImageName() + " "
				+ newScene.getOverlays() + " " + newScene.getName() + " That's all folks!");
		return sceneRepository.save(newScene);
	}

	// Single item

	@GetMapping("/scenes/{id}")
	SceneModel one(@PathVariable Long id) {

		return sceneRepository.findById(id).orElseThrow(() -> new SceneNotFoundException(id));
		// orElseThrow();
	}

	// To Check again
	@PutMapping("/scenes/{id}")
	SceneModel replaceSceneModel(@RequestBody SceneModel newScene, @PathVariable Long id) {

		return sceneRepository.findById(id).map(scene -> {
			scene.setName(newScene.getName());
			scene.setImageId(newScene.getImageId());
			return sceneRepository.save(scene);
		}).orElseGet(() -> {
			newScene.setId(id);
			return sceneRepository.save(newScene);
		});
	}

	@DeleteMapping("/deletescene/{id}")
	void deleteEmployee(@PathVariable Long id) {
		sceneRepository.deleteById(id);

	}

}
