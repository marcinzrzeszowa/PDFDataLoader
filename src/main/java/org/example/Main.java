package org.example;

import org.example.structure.ApplicationLoader;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        ApplicationLoader application = new ApplicationLoader(new TestApplication());
        application.start();
    }

/*

//przykład łamiący zasadę otwarte zamknięte
public class CreateDocument
{
    public void CreateDoc(string type, List<Product> products)
    {
        switch (type)
        {
            case "invoice":
                CreateInvoice(products);
                break;
            case "offer":
                CreateOffer(products);
                break;
            case "order":
                CreateOrder(products);
                break;
            default:
                break;
         }
    }

    private void CreateInvoiceDoc(List<Product> products)
    {
        // tresc metody
    }

    private void CreateOfferDoc(List<Product> products)
    {
        // tresc metody
    }

    private void CreateOrderDoc(List<Product> products)
    {
        // tresc metody
    }
}

    //przykład z uwzględnieniem zasady otwarte zamknięte
    public abstract class CreateDocument
    {
        public abstract void CreateDoc(List<Product> products)  { }
    }

    public class CreateInvoiceDocument : CreateDocument
    {
        public override CreateDoc(List<Product> products)
        {
            // tresc metody w klasie dotyczącej faktur
        }
    }

    public class CreateOfferDocument : CreateDocument
    {
        public override CreateDoc(List<Product> products)
        {
            // tresc metody w klasie dotyczącej ofert
        }
    }

    public class CreateOrderDocument : CreateDocument
    {
        public override CreateDoc(List<Product> products)
        {
            // tresc metody w klasie dotyczącej zapytań
        }
    }

    public class CreateInvoiceProformDocument : CreateDocument
    {
        public override CreateDoc(List<Product> products)
        {
            // tresc metody w klasie dotyczącej faktur proform
        }
    }*/
}