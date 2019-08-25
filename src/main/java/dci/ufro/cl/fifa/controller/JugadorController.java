package dci.ufro.cl.fifa.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/{nombre}/estadisticas")
public class JugadorController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String mostrarJugador(@PathVariable String nombre, Model modelo) {
		// TODO - implement JugadorController.mostrarJugador
		throw new UnsupportedOperationException();
	}



}