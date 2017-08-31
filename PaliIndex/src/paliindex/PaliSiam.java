/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paliindex;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Thanakrit-Promsiri
 */
@Entity
@Table(name = "pali_siam")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaliSiam.findAll", query = "SELECT p FROM PaliSiam p")
    , @NamedQuery(name = "PaliSiam.findById", query = "SELECT p FROM PaliSiam p WHERE p.id = :id")
    , @NamedQuery(name = "PaliSiam.findByVolume", query = "SELECT p FROM PaliSiam p WHERE p.volume = :volume")
    , @NamedQuery(name = "PaliSiam.findByPage", query = "SELECT p FROM PaliSiam p WHERE p.page = :page")
    , @NamedQuery(name = "PaliSiam.findByItems", query = "SELECT p FROM PaliSiam p WHERE p.items = :items")})
public class PaliSiam implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "volume")
    private int volume;
    @Basic(optional = false)
    @Column(name = "page")
    private int page;
    @Basic(optional = false)
    @Column(name = "items")
    private String items;
    @Basic(optional = false)
    @Lob
    @Column(name = "content")
    private String content;

    public PaliSiam() {
    }

    public PaliSiam(Integer id) {
        this.id = id;
    }

    public PaliSiam(Integer id, int volume, int page, String items, String content) {
        this.id = id;
        this.volume = volume;
        this.page = page;
        this.items = items;
        this.content = content;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaliSiam)) {
            return false;
        }
        PaliSiam other = (PaliSiam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "paliindex.PaliSiam[ id=" + id + " ]";
    }
    
}
