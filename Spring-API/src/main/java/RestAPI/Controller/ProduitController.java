package RestAPI.Controller;

import RestAPI.DAO.ProduitRepository;
import RestAPI.DO.ProduitDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProduitController {
    @Autowired
    ProduitRepository produitRepository;
    @GetMapping("/produits")
    public List<ProduitDO> getAll(@RequestParam(required = false,defaultValue = "") String search){
        if(search =="") {
            System.err.println("Voici la liste des produits");
            return produitRepository.findAll();
        }else{
            System.err.println("Voici la liste des produits");
            System.err.println("keyword:"+search);
            return produitRepository.searchProduit(search);
        }
    }
    @GetMapping("/produits/{id}")
    public ProduitDO getById(@PathVariable long id){
        ProduitDO produit=produitRepository.findById(id).get();
        System.err.println("Voici votre produit n°: "+id);
        return produit;
    }

    @PostMapping("/admin/produits")
    public ProduitDO addProduit(@RequestBody ProduitDO produit){
        produitRepository.save(produit);
        System.err.println("Le produit à bien été ajouter");
        return produit;
    }

    @PutMapping("/admin/produits")
    public void updateProduit(@RequestBody ProduitDO produit){
        System.err.println("hello");
        produitRepository.save(produit);
        System.err.println("Le produit à bien été modifier");
    }

    @DeleteMapping("/admin/produits/{id}")
    public void deleteProduit(@PathVariable long id){
        produitRepository.deleteById(id);
        System.err.println("Le produit à bien été supprimer");
    }


}
