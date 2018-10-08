package com.geo.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.geo.entities.Navigation;
import com.geo.models.Nav;
import com.geo.service.NavigationService;

@RestController
@RequestMapping(value = "/navigation")
public class NavigationRestController {

	@Autowired
	NavigationService navigationService; // Service which will do all data
	// retrieval/manipulation work

	// -------------------Retrieve All
	// Navigations--------------------------------------------------------

	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Navigation>> listAllNavigations() {
		List<Navigation> navigations = navigationService.findAll();
		if (navigations.isEmpty()) {
			return new ResponseEntity<List<Navigation>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Navigation>>(navigations, HttpStatus.OK);
	}

	// -------------------Retrieve Single
	// Navigation--------------------------------------------------------

	@RequestMapping(value = "{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<Navigation> getNavigation(@PathVariable("id") long id) {
		System.out.println("Fetching Navigation with id " + id);
		Navigation navigation = navigationService.findById(id);
		if (navigation == null) {
			System.out.println("Navigation with id " + id + " not found");
			return new ResponseEntity<Navigation>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Navigation>(navigation, HttpStatus.OK);
	}

	// -------------------Create a
	// Navigation--------------------------------------------------------

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<Void> createNavigation(@RequestBody Navigation navigation, UriComponentsBuilder ucBuilder) {
		System.out.println("Creating Navigation " + navigation.getNameToken());

		if (navigationService.isExist(navigation)) {
			System.out.println("A Navigation with name " + navigation.getNameToken() + " already exist");
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}

		navigationService.save(navigation);

		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/navigation/{id}").buildAndExpand(navigation.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}

	@RequestMapping(value = "/findAllByRoles", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Navigation>> listAllByRoles(@RequestParam("roleIds") List<Long> roleIds) {
		List<Navigation> navigations = navigationService.findAllByRoles(roleIds);
		if (navigations.isEmpty()) {
			return new ResponseEntity<List<Navigation>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Navigation>>(navigations, HttpStatus.OK);
	}

	@RequestMapping(value = "/findAllByParent", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Navigation>> listAllByParent(@RequestParam("parentId") Long parentId) {
		List<Navigation> navigations = navigationService.findAllByParent(parentId);
		if (navigations.isEmpty()) {
			return new ResponseEntity<List<Navigation>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Navigation>>(navigations, HttpStatus.OK);
	}

	@RequestMapping(value = "/findAllByUser/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Navigation>> listAllByUserId(@PathVariable("userId") Long userId) {
		List<Navigation> navigations = navigationService.findAllByUser(userId);
		if (navigations.isEmpty()) {
			return new ResponseEntity<List<Navigation>>(HttpStatus.NO_CONTENT);// You
		}
		return new ResponseEntity<List<Navigation>>(navigations, HttpStatus.OK);
	}

	@RequestMapping(value = "/findAllByUserTree/{userId}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<List<Nav>> listAllByUserId2(@PathVariable("userId") Long userId) {
		List<Navigation> navigations = navigationService.findAllByUser(userId);
		if (navigations.isEmpty()) {
			return new ResponseEntity<List<Nav>>(HttpStatus.NO_CONTENT);// You
		}

		return new ResponseEntity<List<Nav>>(createNavigationTree(navigations), HttpStatus.OK);
	}
	
	
	
	
	public List<Nav> createNavigationTree(List<Navigation> navigations) {
		List<Nav> parentNavs = new ArrayList<>();
		List<Nav> childNavs = new ArrayList<>();
		for (Navigation navigation : navigations) {
			if (navigation.getParent() == null) {
				parentNavs.add(new Nav(navigation));
			}else {
				childNavs.add(new Nav(navigation));
			}
		}
		for (Nav nav : parentNavs) {
			nav.setChNavs(getChildTree(nav,childNavs));
		}
		return parentNavs;
	}
	
	public List<Nav> getChildTree(Nav nav,List<Nav> childNavs) {
		List<Nav> navs=new ArrayList<>();
		
		for (Nav childNavs2 : childNavs) {
			if (childNavs2.getParentId()==nav.getId()) {
				navs.add(childNavs2);
			}
		}
		
		if (navs.size() > 0) {
			for (Nav nav2 : navs) {
				nav2.setChNavs(getChildTree(nav2, childNavs));
			}
		}
		
		return navs;
	}

}