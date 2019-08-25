package dci.ufro.cl.fifa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/{nombre}/conmparacion")
public class ComparacionController {


	@RequestMapping(value="", method = RequestMethod.GET)
	public String comparacion(@PathVariable String nombre , Model modelo) {
		// TODO - implement ComparacionController.comparacion
		throw new UnsupportedOperationException();
	}
	@RequestMapping(value="/{otrojugador}", method = RequestMethod.GET)
	public String otroJugador(@PathVariable("otrojugador") String nombre){
		throw new UnsupportedOperationException();
	}

	@RequestMapping(value="/{otrojugador}", method = RequestMethod.POST)
	public String otroJugador(@PathVariable("otrojugador") String nombre, Model modelo){
		throw new UnsupportedOperationException();
	}

}