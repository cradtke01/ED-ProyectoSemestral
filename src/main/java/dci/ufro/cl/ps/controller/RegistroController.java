package dci.ufro.cl.ps.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/{nombre}/estadisticas")
public class RegistroController {

	@RequestMapping(value = "", method = RequestMethod.GET)
	public String mostrarRegistro(@PathVariable String nombre, Model modelo) {
		// TODO - implement RegistroController.mostrarRegistro
		throw new UnsupportedOperationException();
	}



}