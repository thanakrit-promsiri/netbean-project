/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package paliindex;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import paliindex.exceptions.NonexistentEntityException;

/**
 *
 * @author Thanakrit-Promsiri
 */
public class PaliSiamJpaController implements Serializable {

    public PaliSiamJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PaliSiam paliSiam) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(paliSiam);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PaliSiam paliSiam) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            paliSiam = em.merge(paliSiam);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = paliSiam.getId();
                if (findPaliSiam(id) == null) {
                    throw new NonexistentEntityException("The paliSiam with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PaliSiam paliSiam;
            try {
                paliSiam = em.getReference(PaliSiam.class, id);
                paliSiam.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The paliSiam with id " + id + " no longer exists.", enfe);
            }
            em.remove(paliSiam);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PaliSiam> findPaliSiamEntities() {
        return findPaliSiamEntities(true, -1, -1);
    }

    public List<PaliSiam> findPaliSiamEntities(int maxResults, int firstResult) {
        return findPaliSiamEntities(false, maxResults, firstResult);
    }

    private List<PaliSiam> findPaliSiamEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PaliSiam.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PaliSiam findPaliSiam(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PaliSiam.class, id);
        } finally {
            em.close();
        }
    }

    public int getPaliSiamCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PaliSiam> rt = cq.from(PaliSiam.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
