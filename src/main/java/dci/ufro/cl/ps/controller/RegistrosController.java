package dci.ufro.cl.ps.controller;

import dci.ufro.cl.ps.model.ListaRegistros;
import dci.ufro.cl.ps.model.Registro;
import dci.ufro.cl.ps.services.ManejoDato;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;

//define la clase como un controlador
//direccion que desencadena el controlador
@Controller
@RequestMapping("")
public class RegistrosController {

    private Collection<Registro> registros;

    @RequestMapping("/index")//direccion que desencadena el metodo
    public String index(Model modelo, @RequestParam(name = "fecha", required = false) String fecha, @RequestParam(name = "hora", required = false) String hora) {
        ManejoDato.resumirDatos(fecha, hora);
        modelo.addAttribute("fecha", fecha);
        modelo.addAttribute("hora", hora);
        modelo.addAttribute("listaRegistros", ListaRegistros.getListaRegistros());
        return "index";
    }
}