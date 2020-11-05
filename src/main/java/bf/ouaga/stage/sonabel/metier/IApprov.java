package bf.ouaga.stage.sonabel.metier;

public interface IApprov {

   public Long getCeodeMage(String nom);
    
   public Long findByCodeExplParent(String codeExplParent);
   
   public Long getNumEquipe(String nom);
   
   
}
