package me.hao0.basic.other;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class JTreeTest
{
    public JTreeTest()
    {
        JFrame f = new JFrame("TreeDemo");
        Container contentPane = f.getContentPane();
        
        
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("��Դ������");
        DefaultMutableTreeNode node1 = new DefaultMutableTreeNode("�ҵĹ��İ�");
        DefaultMutableTreeNode node2 = new DefaultMutableTreeNode("�ҵĵ���");
        DefaultMutableTreeNode node3 = new DefaultMutableTreeNode("�ղؼ�");
        DefaultMutableTreeNode node4 = new DefaultMutableTreeNode("Readme");
        
        DefaultTreeModel treeModel = new DefaultTreeModel(root);

        /*DefaultTreeModel�����ṩ��insertNodeInto()��������ڵ㵽���ڵ������.
         *����DefaultMutableTreeNode�����ṩ��getChildCount()����ȡ��Ŀǰ�ӽڵ������.
         */
        treeModel.insertNodeInto(node1, root, root.getChildCount());
        treeModel.insertNodeInto(node2, root, root.getChildCount());
        treeModel.insertNodeInto(node3, root, root.getChildCount());
        treeModel.insertNodeInto(node4, root, root.getChildCount());
        
        DefaultMutableTreeNode leafnode = new 
                DefaultMutableTreeNode("��˾�ļ�");

        //DefaultTreeModel�����ṩ��insertNodeInto()��������ڵ㵽���ڵ������.
        treeModel.insertNodeInto(leafnode, node1, node1.getChildCount());
        leafnode = new DefaultMutableTreeNode("�����ż�");
        treeModel.insertNodeInto(leafnode, node1, node1.getChildCount());
        leafnode = new DefaultMutableTreeNode("˽���ļ�");
        treeModel.insertNodeInto(leafnode, node1, node1.getChildCount());
        
        leafnode = new DefaultMutableTreeNode("�������(C:)");
        treeModel.insertNodeInto(leafnode, node2, node2.getChildCount());
        leafnode = new DefaultMutableTreeNode("�������(D:)");
        treeModel.insertNodeInto(leafnode, node2, node2.getChildCount());
        leafnode = new DefaultMutableTreeNode("�������(E:)");
        treeModel.insertNodeInto(leafnode, node2, node2.getChildCount());
        
        DefaultMutableTreeNode node31 = new DefaultMutableTreeNode("��վ�б�");
        treeModel.insertNodeInto(node31, node3, node3.getChildCount());
        leafnode = new DefaultMutableTreeNode("��Ħվ");
        treeModel.insertNodeInto(leafnode, node3, node3.getChildCount());
        leafnode = new DefaultMutableTreeNode("ְ����Ϣ");
        treeModel.insertNodeInto(leafnode, node3, node3.getChildCount());
        leafnode = new DefaultMutableTreeNode("�������");
        treeModel.insertNodeInto(leafnode, node3, node3.getChildCount());
       
         // this line needs to be implemented in order to make JWS work properly
          //UIManager.getLookAndFeelDefaults().put("ClassLoader", getClass().getClassLoader());
        
        //��TreeModel����JTree��
        JTree tree = new JTree(treeModel);
        /*�ı�JTree�����**/
          tree.putClientProperty("JTree.lineStyle","Horizontal");
         // tree.putClientProperty("JTree.lineStyle","Angled");
          
        //�ı�ڵ�ͼ��:
          
          /*DefaultTreeCellRenderer cellRenderer=(DefaultTreeCellRenderer)tree.getCellRenderer();
          cellRenderer.setLeafIcon(new ImageIcon("images\\teacher.jpg"));
          cellRenderer.setOpenIcon(new ImageIcon("images\\teacher.jpg"));
          cellRenderer.setClosedIcon(new ImageIcon("images\\teacher.jpg"));
          
          cellRenderer.setFont(new Font("����",Font.PLAIN,12));//��������.
          cellRenderer.setBackgroundNonSelectionColor(Color.white);
          cellRenderer.setBackgroundSelectionColor(Color.yellow);
          cellRenderer.setBorderSelectionColor(Color.red);
          	����ѡʱ��ѡʱ�����ֵı仯��ɫ
           
          cellRenderer.setTextNonSelectionColor(Color.black);
          cellRenderer.setTextSelectionColor(Color.blue);*/

        /*�ı�JTree�����**/
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(tree);
        
        contentPane.add(scrollPane);
        f.pack();
        f.setVisible(true);
        
        f.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

    }

    public static void main(String args[]) {
    
        new JTreeTest();
    }
}
