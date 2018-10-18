package com.geo;

import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.sql.DataSource;

import org.apache.commons.io.IOUtils;
//import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.util.ResourceUtils;

import com.geo.config.profile.EnvBasedConfig;
import com.geo.entities.Address;
import com.geo.entities.Navigation;
import com.geo.entities.Role;
import com.geo.entities.RoleNavigation;
import com.geo.models.NodeType;
import com.geo.models.google.Geocode;
import com.geo.service.AddressService;
import com.geo.service.NavigationService;
import com.geo.service.RoleNavigationService;
import com.geo.service.RoleService;
import com.geo.service.UserService;
import com.google.gson.Gson;
import com.opencsv.CSVReader;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class SpringGeolocationApplication implements CommandLineRunner {

	protected final Log logger = LogFactory.getLog(getClass());

	@Autowired
	UserService userService;

	@Autowired
	RoleService roleService;

	@Autowired
	AddressService addressService;

	@Autowired
	NavigationService navigationService;

	@Autowired
	RoleNavigationService roleNavigationService;
	
	
	@Autowired
	DataSource dataSource;
	
	@Autowired
	EnvBasedConfig envBasedConfig;

	private static final String URL = "https://maps.googleapis.com/maps/api/geocode/json";

	public static void main(String[] args) {
		SpringApplication.run(SpringGeolocationApplication.class, args);

	}

	@Override
	public void run(String... arg0) throws Exception {
		logger.debug("hello");
		System.err.println("run() started");
		System.err.println("Datasource:"+dataSource);
		envBasedConfig.setUp();

//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//		Role adminRole = roleService.findByRoleName("admin");
//
//		if (adminRole == null) {
//			adminRole = new Role();
//			adminRole.setRoleName("admin");
//			adminRole = roleService.save(adminRole);
//		} else {
//			System.err.println("role already exist " + adminRole);
//		}
//
//		Role userRole = roleService.findByRoleName("user");
//
//		if (userRole == null) {
//			userRole = new Role();
//			userRole.setRoleName("user");
//			userRole = roleService.save(userRole);
//		} else {
//			System.err.println("role2 already exist " + userRole);
//		}
//
//		Role superuserRole = roleService.findByRoleName("superuser");
//
//		if (superuserRole == null) {
//			superuserRole = new Role();
//			superuserRole.setRoleName("superuser");
//			superuserRole = roleService.save(superuserRole);
//		} else {
//			System.err.println("role3 already exist " + superuserRole);
//		}
//
//		User user = userService.findByLogin("pintu");
//		if (user == null) {
//			user = new User();
//			user.setAcive(true);
//			user.setEmail("niranjanpanigrahi2009@gmail.com");
//			user.setFirstName("Niranjan");
//			user.setLastName("Panigrahi");
//			user.setLogin("pintu");
//			user.setPassword(encoder.encode("pintu12345"));
//			user.setPhone("8951560216");
//			Set<Role> roles = new HashSet<>();
//			roles.add(superuserRole);
//			roles.add(adminRole);
//			roles.add(userRole);
//			user.setRoles(roles);
//			user = userService.save(user);
//		} else {
//			System.err.println("user1 already exist " + user);
//		}
//
//		User user2 = userService.findByLogin("satya");
//		if (user2 == null) {
//			user2 = new User();
//			user2.setAcive(true);
//			user2.setEmail("satyamishra@gmail.com");
//			user2.setFirstName("Satya");
//			user2.setLastName("Mishra");
//			user2.setLogin("satya");
//			user2.setPassword(encoder.encode("satya12345"));
//			user2.setPhone("8888888888");
//			Set<Role> roles2 = new HashSet<>();
//			roles2.add(adminRole);
//			user2.setRoles(roles2);
//			user2 = userService.save(user2);
//		} else {
//			System.err.println("user2 already exist " + user2);
//		}
//
//		User user3 = userService.findByLogin("amit");
//		if (user3 == null) {
//			user3 = new User();
//			user3.setAcive(true);
//			user3.setEmail("amit@gmail.com");
//			user3.setFirstName("Amit");
//			user3.setLastName("Saha");
//			user3.setLogin("amit");
//			user3.setPassword(encoder.encode("amit12345"));
//			user3.setPhone("9999999999");
//			Set<Role> roles3 = new HashSet<>();
//			roles3.add(userRole);
//			user3.setRoles(roles3);
//			user3 = userService.save(user3);
//		} else {
//			System.err.println("user3 already exist " + user3);
//		}
//
//		//
//		insertAddress();
//
//		insertDummyMenu();
//
//		insertSuperuserRoleNavigation();
//
//		insertAdminRoleNavigation();
//
//		insertUserRoleNavigation();

		System.err.println("run() ended");
	}

	// reverse geo coding

	// private String getAddressByGpsCoordinates(String lng, String lat)
	// throws MalformedURLException, IOException,
	// org.json.simple.parser.ParseException {
	//
	// URL url = new URL(
	// "http://maps.googleapis.com/maps/api/geocode/json?latlng=" + lat + "," + lng
	// + "&sensor=true");
	// HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
	// String formattedAddress = "";
	//
	// try {
	// InputStream in = url.openStream();
	// BufferedReader reader = new BufferedReader(new InputStreamReader(in));
	// String result, line = reader.readLine();
	// result = line;
	// while ((line = reader.readLine()) != null) {
	// result += line;
	// }
	//
	// JSONParser parser = new JSONParser();
	// JSONObject rsp = (JSONObject) parser.parse(result);
	//
	// if (rsp.containsKey("results")) {
	// JSONArray matches = (JSONArray) rsp.get("results");
	// JSONObject data = (JSONObject) matches.get(0); // TODO: check if idx=0 exists
	// formattedAddress = (String) data.get("formatted_address");
	// }
	//
	// return "";
	// } finally {
	// urlConnection.disconnect();
	// return formattedAddress;
	// }
	// }

	// geo coding

	public Geocode getJSONByGoogle(String fullAddress) throws IOException {

		/*
		 * Create an java.net.URL object by passing the request URL in constructor. Here
		 * you can see I am converting the fullAddress String in UTF-8 format. You will
		 * get Exception if you don't convert your address in UTF-8 format. Perhaps
		 * google loves UTF-8 format. :) In parameter we also need to pass "sensor"
		 * parameter. sensor (required parameter) — Indicates whether or not the
		 * geocoding request comes from a device with a location sensor. This value must
		 * be either true or false.
		 */

		/*
		 * Here the fullAddress String is in format like "address,city,state,zipcode".
		 * Here address means "street number + route" .
		 *
		 */
		URL url = new URL(URL + "?address=" + URLEncoder.encode(fullAddress, "UTF-8") + "&sensor=false"
				+ "&key=AIzaSyDbcJ-HDROUl8lgUHizqbqPQ7B3LTNejlc");

		// Open the Connection
		URLConnection conn = url.openConnection();

		// This is Simple a byte array output stream that we will use to keep the output
		// data from google.
		ByteArrayOutputStream output = new ByteArrayOutputStream(1024);

		// copying the output data from Google which will be either in JSON or XML
		// depending on your request URL that in which format you have requested.
		IOUtils.copy(conn.getInputStream(), output);

		// close the byte array output stream now.
		output.close();

		Gson gson = new Gson();
		Geocode geocode = gson.fromJson(output.toString(), Geocode.class);
		System.err.println(output.toString());
		return geocode; // This returned String is JSON string from which you can retrieve all key value
						// pair and can save it in POJO.
	}

	// insert address

	public void insertAddress() {
		CSVReader reader = null;
		try {
			reader = new CSVReader(new FileReader(ResourceUtils.getFile("classpath:us-500.csv")));
			String[] line;
			int count = -1;
			while ((line = reader.readNext()) != null) {
				count++;
				if (count == 0) {
					continue;
				}

				double longitude = Math.random() * Math.PI * 2;
				double latitude = Math.acos(Math.random() * 2 - 1);

				Address address = new Address();
				address.setActive(true);
				address.setStreet(line[3]);
				address.setAddressLineOne(line[2]);
				address.setAddressLineTwo(line[8] + "," + line[9] + "," + line[10]);
				address.setDistrict(line[4]);
				address.setCountry(line[5]);
				address.setState(line[6]);
				address.setPincode(line[7]);
				address.setGpsLocationLatitude(latitude + "");
				address.setGpsLocationLongitude(longitude + "");
				address = addressService.save(address);

				System.out.println(address);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void insertDummyMenu() {

		Navigation navigation = new Navigation();
		navigation.setIconImage("tachometer");
		navigation.setNameToken("dashboard");
		navigation.setParent(null);
		navigation.setScreenLabel("Dashboard");
		navigation.setSequenceNo(1);
		navigation.setScreenType(NodeType.LEAF.getValue());
		navigation = navigationService.save(navigation);

		Navigation navigation2 = new Navigation();
		navigation2.setIconImage("map-marker");
		navigation2.setNameToken("maps");
		navigation2.setParent(null);
		navigation2.setScreenLabel("Maps");
		navigation2.setSequenceNo(2);
		navigation2.setScreenType(NodeType.NODE.getValue());
		navigation2 = navigationService.save(navigation2);

		Navigation navigation3 = new Navigation();
		navigation3.setIconImage("globe");
		navigation3.setNameToken("services");
		navigation3.setParent(null);
		navigation3.setScreenLabel("Services");
		navigation3.setSequenceNo(3);
		navigation3.setScreenType(NodeType.NODE.getValue());
		navigationService.save(navigation3);

		Navigation navigation4 = new Navigation();
		navigation4.setIconImage("car");
		navigation4.setNameToken("new");
		navigation4.setParent(null);
		navigation4.setScreenLabel("New");
		navigation4.setSequenceNo(4);
		navigation4.setScreenType(NodeType.NODE.getValue());
		navigationService.save(navigation4);

		Navigation navigation5 = new Navigation();
		navigation5.setIconImage("user");
		navigation5.setNameToken("profile");
		navigation5.setParent(null);
		navigation5.setScreenLabel("Profile");
		navigation5.setSequenceNo(5);
		navigation5.setScreenType(NodeType.LEAF.getValue());
		navigationService.save(navigation5);

		Navigation navigation6 = new Navigation();
		navigation6.setIconImage("users");
		navigation6.setNameToken("users");
		navigation6.setParent(null);
		navigation6.setScreenLabel("Users");
		navigation6.setSequenceNo(6);
		navigation6.setScreenType(NodeType.LEAF.getValue());
		navigation6 = navigationService.save(navigation6);

		// Map children

		Navigation navigation7 = new Navigation();
		navigation7.setIconImage("icon.png");
		navigation7.setNameToken("mapone");
		navigation7.setParent(navigation2);
		navigation7.setScreenLabel("Map One");
		navigation7.setSequenceNo(7);
		navigation7.setScreenType(NodeType.LEAF.getValue());
		navigationService.save(navigation7);

		Navigation navigation8 = new Navigation();
		navigation8.setIconImage("icon.png");
		navigation8.setNameToken("maptwo");
		navigation8.setParent(navigation2);
		navigation8.setScreenLabel("Map Two");
		navigation8.setSequenceNo(8);
		navigation8.setScreenType(NodeType.LEAF.getValue());
		navigationService.save(navigation8);

		Navigation navigation9 = new Navigation();
		navigation9.setIconImage("icon.png");
		navigation9.setNameToken("mapthree");
		navigation9.setParent(navigation2);
		navigation9.setScreenLabel("LEVEL2 NODE4");
		navigation9.setSequenceNo(9);
		navigation9.setScreenType(NodeType.LEAF.getValue());
		navigationService.save(navigation9);

		// Service children

		Navigation navigation10 = new Navigation();
		navigation10.setIconImage("icon.png");
		navigation10.setNameToken("serviceone");
		navigation10.setParent(navigation3);
		navigation10.setScreenLabel("Service One");
		navigation10.setSequenceNo(10);
		navigation10.setScreenType(NodeType.LEAF.getValue());
		navigationService.save(navigation10);

		Navigation navigation11 = new Navigation();
		navigation11.setIconImage("icon.png");
		navigation11.setNameToken("servicetwo");
		navigation11.setParent(navigation3);
		navigation11.setScreenLabel("Service Two");
		navigation11.setSequenceNo(11);
		navigation11.setScreenType(NodeType.LEAF.getValue());
		navigationService.save(navigation11);

		// New children

		Navigation navigation12 = new Navigation();
		navigation12.setIconImage("icon.png");
		navigation12.setNameToken("newone");
		navigation12.setParent(navigation4);
		navigation12.setScreenLabel("New One");
		navigation12.setSequenceNo(10);
		navigation12.setScreenType(NodeType.LEAF.getValue());
		navigationService.save(navigation12);

		Navigation navigation13 = new Navigation();
		navigation13.setIconImage("icon.png");
		navigation13.setNameToken("newtwo");
		navigation13.setParent(navigation4);
		navigation13.setScreenLabel("New Two");
		navigation13.setSequenceNo(11);
		navigation13.setScreenType(NodeType.LEAF.getValue());
		navigationService.save(navigation13);

	}

	public void insertSuperuserRoleNavigation() {

		// for role admin

		Role superuser = roleService.findByRoleName("superuser");

		// find all navigation

		Navigation navigation = navigationService.findByNameToken("dashboard");
		Navigation navigation2 = navigationService.findByNameToken("maps");
		Navigation navigation3 = navigationService.findByNameToken("services");
		Navigation navigation4 = navigationService.findByNameToken("new");
		Navigation navigation5 = navigationService.findByNameToken("profile");
		Navigation navigation6 = navigationService.findByNameToken("users");
		Navigation navigation7 = navigationService.findByNameToken("mapone");
		Navigation navigation8 = navigationService.findByNameToken("maptwo");
		Navigation navigation9 = navigationService.findByNameToken("mapthree");
		Navigation navigation10 = navigationService.findByNameToken("serviceone");
		Navigation navigation11 = navigationService.findByNameToken("servicetwo");
		Navigation navigation12 = navigationService.findByNameToken("newone");
		Navigation navigation13 = navigationService.findByNameToken("newtwo");

		RoleNavigation roleNavigation = new RoleNavigation();
		roleNavigation.setRole(superuser);
		roleNavigation.setNavigation(navigation);
		roleNavigationService.save(roleNavigation);

		RoleNavigation roleNavigation2 = new RoleNavigation();
		roleNavigation2.setRole(superuser);
		roleNavigation2.setNavigation(navigation2);
		roleNavigationService.save(roleNavigation2);

		RoleNavigation roleNavigation3 = new RoleNavigation();
		roleNavigation3.setRole(superuser);
		roleNavigation3.setNavigation(navigation3);
		roleNavigationService.save(roleNavigation3);

		RoleNavigation roleNavigation4 = new RoleNavigation();
		roleNavigation4.setRole(superuser);
		roleNavigation4.setNavigation(navigation4);
		roleNavigationService.save(roleNavigation4);

		RoleNavigation roleNavigation5 = new RoleNavigation();
		roleNavigation5.setRole(superuser);
		roleNavigation5.setNavigation(navigation5);
		roleNavigationService.save(roleNavigation5);

		RoleNavigation roleNavigation6 = new RoleNavigation();
		roleNavigation6.setRole(superuser);
		roleNavigation6.setNavigation(navigation6);
		roleNavigationService.save(roleNavigation6);

		RoleNavigation roleNavigation7 = new RoleNavigation();
		roleNavigation7.setRole(superuser);
		roleNavigation7.setNavigation(navigation7);
		roleNavigationService.save(roleNavigation7);

		RoleNavigation roleNavigation8 = new RoleNavigation();
		roleNavigation8.setRole(superuser);
		roleNavigation8.setNavigation(navigation8);
		roleNavigationService.save(roleNavigation8);

		RoleNavigation roleNavigation9 = new RoleNavigation();
		roleNavigation9.setRole(superuser);
		roleNavigation9.setNavigation(navigation9);
		roleNavigationService.save(roleNavigation9);

		RoleNavigation roleNavigation10 = new RoleNavigation();
		roleNavigation10.setRole(superuser);
		roleNavigation10.setNavigation(navigation10);
		roleNavigationService.save(roleNavigation10);

		RoleNavigation roleNavigation11 = new RoleNavigation();
		roleNavigation11.setRole(superuser);
		roleNavigation11.setNavigation(navigation11);
		roleNavigationService.save(roleNavigation11);

		RoleNavigation roleNavigation12 = new RoleNavigation();
		roleNavigation12.setRole(superuser);
		roleNavigation12.setNavigation(navigation12);
		roleNavigationService.save(roleNavigation12);

		RoleNavigation roleNavigation13 = new RoleNavigation();
		roleNavigation13.setRole(superuser);
		roleNavigation13.setNavigation(navigation13);
		roleNavigationService.save(roleNavigation13);

	}

	public void insertAdminRoleNavigation() {

		Role admin = roleService.findByRoleName("admin");

		// find all navigation

		Navigation navigation = navigationService.findByNameToken("dashboard");
		Navigation navigation2 = navigationService.findByNameToken("maps");
		Navigation navigation3 = navigationService.findByNameToken("services");
		// Navigation navigation4 = navigationService.findByNameToken("new");
		Navigation navigation5 = navigationService.findByNameToken("profile");
		Navigation navigation6 = navigationService.findByNameToken("users");
		Navigation navigation7 = navigationService.findByNameToken("mapone");
		Navigation navigation8 = navigationService.findByNameToken("maptwo");
		// Navigation navigation9 = navigationService.findByNameToken("mapthree");
		Navigation navigation10 = navigationService.findByNameToken("serviceone");
		Navigation navigation11 = navigationService.findByNameToken("servicetwo");
		// Navigation navigation12 = navigationService.findByNameToken("newone");
		// Navigation navigation13 = navigationService.findByNameToken("newtwo");

		RoleNavigation roleNavigation = new RoleNavigation();
		roleNavigation.setRole(admin);
		roleNavigation.setNavigation(navigation);
		roleNavigationService.save(roleNavigation);

		RoleNavigation roleNavigation2 = new RoleNavigation();
		roleNavigation2.setRole(admin);
		roleNavigation2.setNavigation(navigation2);
		roleNavigationService.save(roleNavigation2);

		RoleNavigation roleNavigation3 = new RoleNavigation();
		roleNavigation3.setRole(admin);
		roleNavigation3.setNavigation(navigation3);
		roleNavigationService.save(roleNavigation3);

		// RoleNavigation roleNavigation4 = new RoleNavigation();
		// roleNavigation4.setRole(admin);
		// roleNavigation4.setNavigation(navigation4);
		// roleNavigationService.save(roleNavigation4);

		RoleNavigation roleNavigation5 = new RoleNavigation();
		roleNavigation5.setRole(admin);
		roleNavigation5.setNavigation(navigation5);
		roleNavigationService.save(roleNavigation5);

		RoleNavigation roleNavigation6 = new RoleNavigation();
		roleNavigation6.setRole(admin);
		roleNavigation6.setNavigation(navigation6);
		roleNavigationService.save(roleNavigation6);

		RoleNavigation roleNavigation7 = new RoleNavigation();
		roleNavigation7.setRole(admin);
		roleNavigation7.setNavigation(navigation7);
		roleNavigationService.save(roleNavigation7);

		RoleNavigation roleNavigation8 = new RoleNavigation();
		roleNavigation8.setRole(admin);
		roleNavigation8.setNavigation(navigation8);
		roleNavigationService.save(roleNavigation8);

		// RoleNavigation roleNavigation9 = new RoleNavigation();
		// roleNavigation9.setRole(admin);
		// roleNavigation9.setNavigation(navigation9);
		// roleNavigationService.save(roleNavigation9);

		RoleNavigation roleNavigation10 = new RoleNavigation();
		roleNavigation10.setRole(admin);
		roleNavigation10.setNavigation(navigation10);
		roleNavigationService.save(roleNavigation10);

		RoleNavigation roleNavigation11 = new RoleNavigation();
		roleNavigation11.setRole(admin);
		roleNavigation11.setNavigation(navigation11);
		roleNavigationService.save(roleNavigation11);

		// RoleNavigation roleNavigation12 = new RoleNavigation();
		// roleNavigation12.setRole(admin);
		// roleNavigation12.setNavigation(navigation12);
		// roleNavigationService.save(roleNavigation12);
		//
		//
		// RoleNavigation roleNavigation13 = new RoleNavigation();
		// roleNavigation13.setRole(admin);
		// roleNavigation13.setNavigation(navigation13);
		// roleNavigationService.save(roleNavigation13);

	}

	public void insertUserRoleNavigation() {

		Role user = roleService.findByRoleName("user");

		// find all navigation

		Navigation navigation = navigationService.findByNameToken("dashboard");
		Navigation navigation2 = navigationService.findByNameToken("maps");
		Navigation navigation3 = navigationService.findByNameToken("services");
		Navigation navigation4 = navigationService.findByNameToken("new");
		Navigation navigation5 = navigationService.findByNameToken("profile");
		// Navigation navigation6 = navigationService.findByNameToken("users");
		Navigation navigation7 = navigationService.findByNameToken("mapone");
		Navigation navigation8 = navigationService.findByNameToken("maptwo");
		Navigation navigation9 = navigationService.findByNameToken("mapthree");
		// Navigation navigation10 = navigationService.findByNameToken("serviceone");
		// Navigation navigation11 = navigationService.findByNameToken("servicetwo");
		// Navigation navigation12 = navigationService.findByNameToken("newone");
		// Navigation navigation13 = navigationService.findByNameToken("newtwo");

		RoleNavigation roleNavigation = new RoleNavigation();
		roleNavigation.setRole(user);
		roleNavigation.setNavigation(navigation);
		roleNavigationService.save(roleNavigation);

		RoleNavigation roleNavigation2 = new RoleNavigation();
		roleNavigation2.setRole(user);
		roleNavigation2.setNavigation(navigation2);
		roleNavigationService.save(roleNavigation2);

		RoleNavigation roleNavigation3 = new RoleNavigation();
		roleNavigation3.setRole(user);
		roleNavigation3.setNavigation(navigation3);
		roleNavigationService.save(roleNavigation3);

		RoleNavigation roleNavigation4 = new RoleNavigation();
		roleNavigation4.setRole(user);
		roleNavigation4.setNavigation(navigation4);
		roleNavigationService.save(roleNavigation4);

		RoleNavigation roleNavigation5 = new RoleNavigation();
		roleNavigation5.setRole(user);
		roleNavigation5.setNavigation(navigation5);
		roleNavigationService.save(roleNavigation5);

		// RoleNavigation roleNavigation6 = new RoleNavigation();
		// roleNavigation6.setRole(user);
		// roleNavigation6.setNavigation(navigation6);
		// roleNavigationService.save(roleNavigation6);

		RoleNavigation roleNavigation7 = new RoleNavigation();
		roleNavigation7.setRole(user);
		roleNavigation7.setNavigation(navigation7);
		roleNavigationService.save(roleNavigation7);

		RoleNavigation roleNavigation8 = new RoleNavigation();
		roleNavigation8.setRole(user);
		roleNavigation8.setNavigation(navigation8);
		roleNavigationService.save(roleNavigation8);

		RoleNavigation roleNavigation9 = new RoleNavigation();
		roleNavigation9.setRole(user);
		roleNavigation9.setNavigation(navigation9);
		roleNavigationService.save(roleNavigation9);

		// RoleNavigation roleNavigation10 = new RoleNavigation();
		// roleNavigation10.setRole(user);
		// roleNavigation10.setNavigation(navigation10);
		// roleNavigationService.save(roleNavigation10);
		//
		// RoleNavigation roleNavigation11 = new RoleNavigation();
		// roleNavigation11.setRole(user);
		// roleNavigation11.setNavigation(navigation11);
		// roleNavigationService.save(roleNavigation11);

		// RoleNavigation roleNavigation12 = new RoleNavigation();
		// roleNavigation12.setRole(user);
		// roleNavigation12.setNavigation(navigation12);
		// roleNavigationService.save(roleNavigation12);
		//
		//
		// RoleNavigation roleNavigation13 = new RoleNavigation();
		// roleNavigation13.setRole(user);
		// roleNavigation13.setNavigation(navigation13);
		// roleNavigationService.save(roleNavigation13);

	}

}
