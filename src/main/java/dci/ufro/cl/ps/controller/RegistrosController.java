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
    public String index(Model modelo, @RequestParam(name="dia", required=false) String dia, @RequestParam(name="mes", required=false) String mes, @RequestParam(name="ano", required=false) String ano, @RequestParam(name="hora", required=false) String hora) {
        // TODO - implement JugadoresController.index
        ManejoDato.resumirDatos("", dia + "-" + mes + "-" + ano, hora);
        modelo.addAttribute("dia", dia);
        modelo.addAttribute("mes", mes);
        modelo.addAttribute("ano", ano);
        modelo.addAttribute("hora", hora);
        modelo.addAttribute("listaRegistros", ListaRegistros.getListaRegistros());
        return "index";
        //para lanzar una pagina se agrega la pagina en templates y se escribe la direccion donde enviarla
    }
}