package com.group_finity.mascot.imagesetchooser;

import com.group_finity.mascot.Main;
import com.group_finity.mascot.config.Configuration;
import com.group_finity.mascot.config.Entry;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.swing.DefaultListSelectionModel;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;

/**
 * Chooser used to select the Shimeji image sets in use.
 */
public class ImageSetChooser extends javax.swing.JDialog
{
    private final ArrayList<String> imageSets = new ArrayList<>();
    private boolean closeProgram = true; // Whether the program closes on dispose
    private boolean selectAllSets = false; // Default all to selected

    public ImageSetChooser( javax.swing.JFrame parent, boolean modal )
    {
        super( parent, modal );
        initComponents();
        setLocationRelativeTo( null );

        ArrayList<String> activeImageSets = readConfigFile();

        ArrayList<ImageSetChooserPanel> data1 = new ArrayList<>();
        ArrayList<ImageSetChooserPanel> data2 = new ArrayList<>();
        ArrayList<Integer> si1 = new ArrayList<>();
        ArrayList<Integer> si2 = new ArrayList<>();

        // Get list of imagesets (directories under img)
        FilenameFilter fileFilter = (dir, name) -> {
            if( name.equalsIgnoreCase( "unused" ) || name.startsWith( "." ) )
            {
                return false;
            }
            return new File( dir + "/" + name ).isDirectory();
        };
        // Top Level Directory
        String topDir = "./img";
        File dir = new File(topDir);
        String[] children = dir.list( fileFilter );

        // Create ImageSetChooserPanels for ShimejiList
        boolean onList1 = true;	//Toggle adding between the two lists
        int row = 0;	// Current row
        for( String imageSet : children )
        {
            // Determine actions file
            String filePath = "./conf/";
            String actionsFile = filePath + "actions.xml";
            if( new File( filePath + "動作.xml" ).exists( ) )
                actionsFile = filePath + "動作.xml";
            
            filePath = "./conf/" + imageSet + "/";
            if( new File( filePath + "actions.xml" ).exists( ) )
                actionsFile = filePath + "actions.xml";
            if( new File( filePath + "動作.xml" ).exists( ) )
                actionsFile = filePath + "動作.xml";
            if( new File( filePath + "Õïòõ¢£.xml" ).exists( ) )
                actionsFile = filePath + "Õïòõ¢£.xml";
            if( new File( filePath + "¦-º@.xml" ).exists( ) )
                actionsFile = filePath + "¦-º@.xml";
            if( new File( filePath + "ô«ìý.xml" ).exists( ) )
                actionsFile = filePath + "ô«ìý.xml";
            if( new File( filePath + "one.xml" ).exists( ) )
                actionsFile = filePath + "one.xml";
            if( new File( filePath + "1.xml" ).exists( ) )
                actionsFile = filePath + "1.xml";
            
            filePath = "./img/" + imageSet + "/conf/";
            if( new File( filePath + "actions.xml" ).exists( ) )
                actionsFile = filePath + "actions.xml";
            if( new File( filePath + "動作.xml" ).exists( ) )
                actionsFile = filePath + "動作.xml";
            if( new File( filePath + "Õïòõ¢£.xml" ).exists( ) )
                actionsFile = filePath + "Õïòõ¢£.xml";
            if( new File( filePath + "¦-º@.xml" ).exists( ) )
                actionsFile = filePath + "¦-º@.xml";
            if( new File( filePath + "ô«ìý.xml" ).exists( ) )
                actionsFile = filePath + "ô«ìý.xml";
            if( new File( filePath + "one.xml" ).exists( ) )
                actionsFile = filePath + "one.xml";
            if( new File( filePath + "1.xml" ).exists( ) )
                actionsFile = filePath + "1.xml";

            // Determine behaviours file
            filePath = "./conf/";
            String behaviorsFile = filePath + "behaviors.xml";
            if( new File( filePath + "行動.xml" ).exists( ) )
                behaviorsFile = filePath + "行動.xml";
            
            filePath = "./conf/" + imageSet + "/";
            if( new File( filePath + "behaviors.xml" ).exists( ) )
                behaviorsFile = filePath + "behaviors.xml";
            if( new File( filePath + "behavior.xml" ).exists( ) )
                behaviorsFile = filePath + "behavior.xml";
            if( new File( filePath + "行動.xml" ).exists( ) )
                behaviorsFile = filePath + "行動.xml";
            if( new File( filePath + "ÞíîÕïò.xml" ).exists( ) )
                behaviorsFile = filePath + "ÞíîÕïò.xml";
            if( new File( filePath + "ªµ¦-.xml" ).exists( ) )
                behaviorsFile = filePath + "ªµ¦-.xml";
            if( new File( filePath + "ìsô«.xml" ).exists( ) )
                behaviorsFile = filePath + "ìsô«.xml";
            if( new File( filePath + "two.xml" ).exists( ) )
                behaviorsFile = filePath + "two.xml";
            if( new File( filePath + "2.xml" ).exists( ) )
                behaviorsFile = filePath + "2.xml";
            
            filePath = "./img/" + imageSet + "/conf/";
            if( new File( filePath + "behaviors.xml" ).exists( ) )
                behaviorsFile = filePath + "behaviors.xml";
            if( new File( filePath + "behavior.xml" ).exists( ) )
                behaviorsFile = filePath + "behavior.xml";
            if( new File( filePath + "行動.xml" ).exists( ) )
                behaviorsFile = filePath + "行動.xml";
            if( new File( filePath + "ÞíîÕïò.xml" ).exists( ) )
                behaviorsFile = filePath + "ÞíîÕïò.xml";
            if( new File( filePath + "ªµ¦-.xml" ).exists( ) )
                behaviorsFile = filePath + "ªµ¦-.xml";
            if( new File( filePath + "ìsô«.xml" ).exists( ) )
                behaviorsFile = filePath + "ìsô«.xml";
            if( new File( filePath + "two.xml" ).exists( ) )
                behaviorsFile = filePath + "two.xml";
            if( new File( filePath + "2.xml" ).exists( ) )
                behaviorsFile = filePath + "2.xml";
            
            // Determine information file
            filePath = "./conf/";
            String infoFile = filePath + "info.xml";
            
            filePath = "./conf/" + imageSet + "/";
            if( new File( filePath + "info.xml" ).exists( ) )
                infoFile = filePath + "info.xml";
            
            filePath = "./img/" + imageSet + "/conf/";
            if( new File( filePath + "info.xml" ).exists( ) )
                infoFile = filePath + "info.xml";

            String imageFile = topDir + "/" + imageSet + "/Preview.png";
            String caption = imageSet;
            try
            {
                Configuration configuration = new Configuration( );
                
                if( new File( infoFile ).exists( ) )
                {
                    final Document information = DocumentBuilderFactory.newInstance( ).newDocumentBuilder( ).parse( new FileInputStream(infoFile) );

                    configuration.load( new Entry( information.getDocumentElement( ) ), imageSet );
                }
                
                if( configuration.containsInformationKey( configuration.getSchema( ).getString( "Name" ) ) )
                    caption = configuration.getInformation( configuration.getSchema( ).getString( "Name" ) );
                if( configuration.containsInformationKey( configuration.getSchema( ).getString( "PreviewImage" ) ) )
                    imageFile = topDir + "/" + imageSet + "/" + configuration.getInformation( configuration.getSchema( ).getString( "PreviewImage" ) );
            }
            catch( Exception ex )
            {
                imageFile = topDir + "/" + imageSet + "/Preview.png";
                caption = imageSet;
            }

            if( onList1 )
            {
                onList1 = false;
                data1.add( new ImageSetChooserPanel( imageSet, actionsFile,
                                                     behaviorsFile, imageFile, caption ) );
                // Is this set initially selected?
                if( activeImageSets.contains( imageSet ) || selectAllSets )
                {
                    si1.add( row );
                }
            }
            else
            {
                onList1 = true;
                data2.add( new ImageSetChooserPanel( imageSet, actionsFile,
                                                     behaviorsFile, imageFile, caption ) );
                // Is this set initially selected?
                if( activeImageSets.contains( imageSet ) || selectAllSets )
                {
                    si2.add( row );
                }
                row++; //Only increment the row number after the second column
            }
            imageSets.add( imageSet );
        }

        setUpList1();
        jList1.setListData( data1.toArray( new ImageSetChooserPanel[0] ) );
        jList1.setSelectedIndices( convertIntegers( si1 ) );

        setUpList2();
        jList2.setListData( data2.toArray( new ImageSetChooserPanel[0] ) );
        jList2.setSelectedIndices( convertIntegers( si2 ) );
    }

    public ArrayList<String> display( )
    {
        setTitle( Main.getInstance( ).getLanguageBundle( ).getString( "ShimejiImageSetChooser" ) );
        jLabel1.setText( Main.getInstance( ).getLanguageBundle( ).getString( "SelectImageSetsToUse" ) );
        useSelectedButton.setText( Main.getInstance( ).getLanguageBundle( ).getString( "UseSelected" ) );
        useAllButton.setText( Main.getInstance( ).getLanguageBundle( ).getString( "UseAll" ) );
        cancelButton.setText( Main.getInstance( ).getLanguageBundle( ).getString( "Cancel" ) );
        clearAllLabel.setText( Main.getInstance( ).getLanguageBundle( ).getString( "ClearAll" ) );
        selectAllLabel.setText( Main.getInstance( ).getLanguageBundle( ).getString( "SelectAll" ) );
        setVisible( true );
        if( closeProgram )
        {
            return null;
        }
        return imageSets;
    }

    private ArrayList<String> readConfigFile()
    {
        // now with properties style loading!
        ArrayList<String> activeImageSets = new ArrayList<>(Arrays.asList(Main.getInstance().getProperties().getProperty("ActiveShimeji", "").split("/")));
        selectAllSets = activeImageSets.getFirst().trim( ).isEmpty( ); // if no active ones, activate them all!
        return activeImageSets;
    }

    private void updateConfigFile()
    {
        try
        {
            // Config file name
            String configFile = "./conf/settings.properties";
            try (FileOutputStream output = new FileOutputStream(configFile)) {
                Main.getInstance().getProperties().setProperty("ActiveShimeji", imageSets.toString().replace("[", "").replace("]", "").replace(", ", "/"));
                Main.getInstance().getProperties().store(output, "Shimeji-ee Configuration Options");
            }
        }
        catch( Exception e )
        {
            // Doesn't matter at all
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">
    private void initComponents()
    {

        javax.swing.JScrollPane jScrollPane1 = new javax.swing.JScrollPane();
        javax.swing.JPanel jPanel2 = new javax.swing.JPanel();
        jList1 = new ShimejiList();
        jList2 = new ShimejiList();
        jLabel1 = new javax.swing.JLabel();
        javax.swing.JPanel jPanel1 = new javax.swing.JPanel();
        useSelectedButton = new javax.swing.JButton();
        useAllButton = new javax.swing.JButton();
        cancelButton = new javax.swing.JButton();
        javax.swing.JPanel jPanel4 = new javax.swing.JPanel();
        clearAllLabel = new javax.swing.JLabel();
        javax.swing.JLabel slashLabel = new javax.swing.JLabel();
        selectAllLabel = new javax.swing.JLabel();

        setDefaultCloseOperation( javax.swing.WindowConstants.DISPOSE_ON_CLOSE );
        setTitle( "Shimeji-ee Image Set Chooser" );
        setMinimumSize( new java.awt.Dimension( 670, 495 ) );

        jScrollPane1.setPreferredSize( new java.awt.Dimension( 518, 100 ) );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout( jPanel2Layout );
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
                .addGroup( jPanel2Layout.createSequentialGroup()
                .addComponent( jList1, javax.swing.GroupLayout.DEFAULT_SIZE, 298, Short.MAX_VALUE )
                .addGap( 0, 0, 0 )
                .addComponent( jList2, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE ) ) );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
                .addComponent( jList2, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE )
                .addComponent( jList1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE ) );

        jScrollPane1.setViewportView(jPanel2);

        jLabel1.setText( "Select Image Sets to Use:" );

        jPanel1.setLayout( new java.awt.FlowLayout( java.awt.FlowLayout.CENTER, 10, 5 ) );

        useSelectedButton.setText( "Use Selected" );
        useSelectedButton.setMaximumSize( new java.awt.Dimension( 130, 26 ) );
        useSelectedButton.setPreferredSize( new java.awt.Dimension( 130, 26 ) );
        useSelectedButton.addActionListener(evt -> useSelectedButtonActionPerformed( evt ));
        jPanel1.add( useSelectedButton );

        useAllButton.setText( "Use All" );
        useAllButton.setMaximumSize( new java.awt.Dimension( 95, 23 ) );
        useAllButton.setMinimumSize( new java.awt.Dimension( 95, 23 ) );
        useAllButton.setPreferredSize( new java.awt.Dimension( 130, 26 ) );
        useAllButton.addActionListener(evt -> useAllButtonActionPerformed( evt ));
        jPanel1.add( useAllButton );

        cancelButton.setText( "Cancel" );
        cancelButton.setMaximumSize( new java.awt.Dimension( 95, 23 ) );
        cancelButton.setMinimumSize( new java.awt.Dimension( 95, 23 ) );
        cancelButton.setPreferredSize( new java.awt.Dimension( 130, 26 ) );
        cancelButton.addActionListener(evt -> cancelButtonActionPerformed( evt ));
        jPanel1.add( cancelButton );

        jPanel4.setLayout( new javax.swing.BoxLayout(jPanel4, javax.swing.BoxLayout.LINE_AXIS ) );

        clearAllLabel.setForeground( new java.awt.Color( 0, 0, 204 ) );
        clearAllLabel.setText( "Clear All" );
        clearAllLabel.setCursor( new java.awt.Cursor( java.awt.Cursor.HAND_CURSOR ) );
        clearAllLabel.addMouseListener( new java.awt.event.MouseAdapter()
        {
            public void mouseClicked( java.awt.event.MouseEvent evt )
            {
                clearAllLabelMouseClicked( evt );
            }
        } );
        jPanel4.add( clearAllLabel );

        slashLabel.setText( " / " );
        jPanel4.add(slashLabel);

        selectAllLabel.setForeground( new java.awt.Color( 0, 0, 204 ) );
        selectAllLabel.setText( "Select All" );
        selectAllLabel.setCursor( new java.awt.Cursor( java.awt.Cursor.HAND_CURSOR ) );
        selectAllLabel.addMouseListener( new java.awt.event.MouseAdapter()
        {
            public void mouseClicked( java.awt.event.MouseEvent evt )
            {
                selectAllLabelMouseClicked( evt );
            }
        } );
        jPanel4.add( selectAllLabel );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout( getContentPane() );
        getContentPane().setLayout( layout );
        layout.setHorizontalGroup(
                layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
                .addGroup( layout.createSequentialGroup()
                .addContainerGap()
                .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
                .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE )
                .addGroup( layout.createSequentialGroup()
                .addComponent( jLabel1 )
                .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED, 384, Short.MAX_VALUE )
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE ) )
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE ) )
                .addContainerGap() ) );
        layout.setVerticalGroup(
                layout.createParallelGroup( javax.swing.GroupLayout.Alignment.LEADING )
                .addGroup( layout.createSequentialGroup()
                .addContainerGap()
                .addGroup( layout.createParallelGroup( javax.swing.GroupLayout.Alignment.TRAILING )
                .addComponent( jLabel1 )
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE ) )
                .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.RELATED )
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 378, Short.MAX_VALUE )
                .addPreferredGap( javax.swing.LayoutStyle.ComponentPlacement.UNRELATED )
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE )
                .addGap( 11, 11, 11 ) ) );

        pack();
    }// </editor-fold>

    private void clearAllLabelMouseClicked( java.awt.event.MouseEvent evt )
    {
        jList1.clearSelection();
        jList2.clearSelection();
    }

    private void selectAllLabelMouseClicked( java.awt.event.MouseEvent evt )
    {
        jList1.setSelectionInterval( 0, jList1.getModel().getSize() - 1 );
        jList2.setSelectionInterval( 0, jList2.getModel().getSize() - 1 );
    }

    private void useSelectedButtonActionPerformed( java.awt.event.ActionEvent evt )
    {
        imageSets.clear();

        for( ImageSetChooserPanel panel : jList1.getSelectedValuesList() )
        {
            imageSets.add( panel.getImageSetName() );
        }

        for( ImageSetChooserPanel panel : jList2.getSelectedValuesList() )
        {
            imageSets.add( panel.getImageSetName() );
        }

        updateConfigFile();
        closeProgram = false;
        this.dispose();
    }

    private void useAllButtonActionPerformed( java.awt.event.ActionEvent evt )
    {
        closeProgram = false;
        this.dispose();
    }

    private void cancelButtonActionPerformed( java.awt.event.ActionEvent evt )
    {
        this.dispose();
    }

    private int[] convertIntegers( List<Integer> integers )
    {
        int[] ret = new int[ integers.size() ];
        for( int i = 0; i < ret.length; i++ )
        {
            ret[i] = integers.get(i);
        }
        return ret;
    }

    private void setUpList1()
    {
        jList1.setSelectionModel( new DefaultListSelectionModel()
        {
            @Override
            public void setSelectionInterval( int index0, int index1 )
            {
                if( isSelectedIndex( index0 ) )
                {
                    super.removeSelectionInterval( index0, index1 );
                }
                else
                {
                    super.addSelectionInterval( index0, index1 );
                }
            }
        } );
    }

    private void setUpList2()
    {
        jList2.setSelectionModel( new DefaultListSelectionModel()
        {
            @Override
            public void setSelectionInterval( int index0, int index1 )
            {
                if( isSelectedIndex( index0 ) )
                {
                    super.removeSelectionInterval( index0, index1 );
                }
                else
                {
                    super.addSelectionInterval( index0, index1 );
                }
            }
        } );
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        java.awt.EventQueue.invokeLater(() -> {
            new ImageSetChooser( new javax.swing.JFrame(), true ).display( );
            System.exit( 0 );
        });
    }
    // Variables declaration - do not modify
    private javax.swing.JButton cancelButton;
    private javax.swing.JLabel clearAllLabel;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JList<ImageSetChooserPanel> jList1;
    private javax.swing.JList<ImageSetChooserPanel> jList2;
    private javax.swing.JLabel selectAllLabel;
    private javax.swing.JButton useAllButton;
    private javax.swing.JButton useSelectedButton;
    // End of variables declaration
}
