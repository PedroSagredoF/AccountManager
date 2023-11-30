package ps.account.controller;

import jakarta.annotation.PostConstruct;
import jakarta.faces.annotation.View;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import lombok.Data;
import org.primefaces.PrimeFaces;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ps.account.model.Account;
import ps.account.service.AccountService;

import java.util.List;

@Component
@Data
@ViewScoped
public class IndexController {

    @Autowired
    AccountService accountService;
    private List<Account> accounts;
    private Account selectAccount;

    private static final Logger logger = LoggerFactory.getLogger(IndexController.class);

    @PostConstruct
    public void init(){
        loadData();
    }

    public void loadData() {
        this.accounts = accountService.showAccount();
        accounts.forEach((account -> logger.info(account.toString())));
    }

    public void addAccount(){
        logger.info("Create Account");
        this.selectAccount = new Account();
    }

    public void saveAccount(){
        logger.info("Account saved"+this.selectAccount);

        if (this.selectAccount.getId_account() == null){
            this.accountService.saveAccount(this.selectAccount);
            this.accounts.add(this.selectAccount);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Account Added"));
        }else{
            //Update
            this.accountService.saveAccount(this.selectAccount);
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage("Account Updated"));
        }
        //Hide window
        PrimeFaces.current().executeScript("PF('accountWindowMode').hide()");
        //Update Table
        PrimeFaces.current().ajax().update("account-form:messages",
                "account-form:account-table");
        //Reset
        this.selectAccount = null;
    }

    public void deleteAccount(){
        logger.info("Account Deleted: " + this.selectAccount);
        this.accountService.deleteAccount(this.selectAccount);
        // Eliminar el registro de la lista de cuentas
        this.accounts.remove(this.selectAccount);
        // Reset del objeto seleccionado de la tabla
        this.selectAccount = null;
        FacesContext.getCurrentInstance().addMessage(null,
                new FacesMessage("Deleted"));
        PrimeFaces.current().ajax().update("account-form:messages",
                "account-form:account-table");
    }



}
