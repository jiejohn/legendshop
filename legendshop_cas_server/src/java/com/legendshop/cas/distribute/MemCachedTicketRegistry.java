package com.legendshop.cas.distribute;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Future;

import javax.validation.constraints.Min;

import net.spy.memcached.MemcachedClient;

import org.jasig.cas.ticket.ServiceTicket;
import org.jasig.cas.ticket.Ticket;
import org.jasig.cas.ticket.TicketGrantingTicket;
import org.jasig.cas.ticket.registry.AbstractDistributedTicketRegistry;
import org.springframework.beans.factory.DisposableBean;

/**
 * Memcache (or Repcache) backed ticket registry.
 * 
 * @author Scott Battaglia
 * @version $Revision: 1.1 $ $Date: 2005/08/19 18:27:17 $
 * @since 3.3 <BR>
 *        Here I referred to the CAS 3.3 implementation, and override the
 *        getTickets() method, so that the TicketRegistry cleaner will still
 *        work but do nothing more than logging. --George Guo
 * 
 */
public final class MemCachedTicketRegistry extends AbstractDistributedTicketRegistry implements DisposableBean {

	private final MemcachedClient client;

	@Min(0)
	private final int tgtTimeout;

	@Min(0)
	private final int stTimeout;

	private boolean synchronizeUpdatesToRegistry = false;

	/**
	 * Host names should be given in a list of the format:
	 * &lt;hostname&gt;:&lt;port&gt;
	 * 
	 * @param hostnames
	 * @param ticketGrantingTicketTimeOut
	 * @param serviceTicketTimeOut
	 */
	public MemCachedTicketRegistry(final String[] hostnames, final int ticketGrantingTicketTimeOut,
			final int serviceTicketTimeOut) {
		this.tgtTimeout = ticketGrantingTicketTimeOut;
		this.stTimeout = serviceTicketTimeOut;
		final List<InetSocketAddress> addresses = new ArrayList<InetSocketAddress>();

		for (final String hostname : hostnames) {
			String[] hostPort = hostname.split(":");
			addresses.add(new InetSocketAddress(hostPort[0], Integer.parseInt(hostPort[1])));
		}

		try {
			this.client = new MemcachedClient(addresses);
		} catch (final IOException e) {
			throw new IllegalStateException(e);
		}
	}

	/**
	 * This alternative constructor takes time in milliseconds. It has the
	 * timeout parameters first because, otherwise it would be almost impossible
	 * to differentiate the two.
	 * 
	 * @param ticketGrantingTicketTimeOut
	 * @param serviceTicketTimeOut
	 * @param hostnames
	 */
	public MemCachedTicketRegistry(final long ticketGrantingTicketTimeOut, final long serviceTicketTimeOut,
			final String[] hostnames) {
		this(hostnames, (int) (ticketGrantingTicketTimeOut / 1000), (int) (serviceTicketTimeOut / 1000));
	}

	@SuppressWarnings("rawtypes")
	private void handleSynchronousRequest(final Future f) {
		try {
			if (this.synchronizeUpdatesToRegistry) {
				f.get();
			}
		} catch (final Exception e) {
			// ignore these.
		}
	}

	protected void updateTicket(final Ticket ticket) {
		if (ticket instanceof TicketGrantingTicket) {
			handleSynchronousRequest(this.client.replace(ticket.getId(), this.tgtTimeout, ticket));
		}

		if (ticket instanceof ServiceTicket) {
			handleSynchronousRequest(this.client.replace(ticket.getId(), this.stTimeout, ticket));
		}
	}

	public void addTicket(final Ticket ticket) {
		if (ticket instanceof TicketGrantingTicket) {
			handleSynchronousRequest(this.client.add(ticket.getId(), this.tgtTimeout, ticket));
		}

		if (ticket instanceof ServiceTicket) {
			handleSynchronousRequest(this.client.add(ticket.getId(), this.stTimeout, ticket));
		}
	}

	public boolean deleteTicket(final String ticketId) {
		Future<Boolean> f = this.client.delete(ticketId);
		try {
			return f.get().booleanValue();
		} catch (final Exception e) {
			log.error(e.getMessage(), e);
			return false;
		}
	}

	public Ticket getTicket(final String ticketId) {
		final Ticket t = (Ticket) this.client.get(ticketId);
		if (t == null) {
			return null;
		}

		return getProxiedTicketInstance(t);
	}

	/**
	 * This operation is not supported.
	 * 
	 * @throws UnsupportedOperationException
	 *             if you try and call this operation.
	 */
	public Collection<Ticket> getTickets() {
		// throw new UnsupportedOperationException("GetTickets not supported.");

		// return an empty collection
		return new ArrayList<Ticket>(0);
	}

	public void destroy() throws Exception {
		this.client.shutdown();
	}

	public void setSynchronizeUpdatesToRegistry(final boolean b) {
		this.synchronizeUpdatesToRegistry = b;
	}

	protected boolean needsCallback() {
		return true;
	}
}
