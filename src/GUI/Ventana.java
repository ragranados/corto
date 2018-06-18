/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import base.Filtro;
import base.FiltroDao;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javafx.scene.control.ComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author LN710Q
 */
public class Ventana extends JPanel{
    
    public int WIDTH = 900, widthTF = 120, widthB = 100;
    public int HEIGHT = 500, heightTF = 30, heightB = 30;
    public JTextField nombre, codigo, precio, cantidad;
    public JLabel lblnombre,lblcodigo,lblprecio,lblcantidad;
    public JButton actualizar, eliminar, limpiar,insertar,buscar;
    public ComboBox tipo;
    private FiltroDao filtro;
    
    public Ventana(){
        this.filtro = new FiltroDao();
        
        this.lblnombre = new JLabel("nombre");
        lblnombre.setBounds(25,25,70,30);
        
        this.nombre = new JTextField();
        nombre.setBounds(new Rectangle(90,25,widthTF, heightTF));
        
        this.lblcodigo = new JLabel("codigo");
        lblcodigo.setBounds(25,55,70,30);
        
        this.codigo = new JTextField("FY6473");
        codigo.setBounds(new Rectangle(90,55,widthTF, heightTF));
        
        this.lblprecio = new JLabel("precio");
        lblprecio.setBounds(25,85,70,30);
        
        this.precio = new JTextField();
        precio.setBounds(90,85,widthTF, heightTF);
        
        this.lblcantidad = new JLabel("Cantidad");
        lblcantidad.setBounds(25,115,70,30);
        
        this.cantidad = new JTextField();
        cantidad.setBounds(90,115,widthTF, heightTF);
        
        this.actualizar = new JButton("actualizar");
        actualizar.setBounds(250,115,widthTF, heightTF);
        
        this.eliminar = new JButton("eliminar");
        eliminar.setBounds(250+widthTF,115,widthTF, heightTF);
        
        this.limpiar = new JButton("limpiar");
        limpiar.setBounds(250+widthTF*2,115,widthTF, heightTF);
        
        this.insertar = new JButton("insertar");
        insertar.setBounds(250+widthTF*3,115,widthTF, heightTF);
        
        this.buscar = new JButton("buscar");
        buscar.setBounds(250,25,widthTF, heightTF);
        
        buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Filtro f = filtro.read(codigo.getText());
                
                System.out.println(f.getNombre() + " - "+f.getCodigo());
            }
        });
        
        
        
        add(nombre);
        add(lblnombre);
        add(codigo);
        add(lblcodigo);
        add(precio);
        add(lblprecio);
        add(cantidad);
        add(lblcantidad);
        add(actualizar);
        add(eliminar);
        add(limpiar);
        add(insertar);
        add(buscar);
        setLayout(null);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        
    }
    
}
