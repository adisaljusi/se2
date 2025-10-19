<script>
  import { enhance } from "$app/forms";
  let { data, form } = $props();

  let companies = $state(data.companies);

  $effect(() => {
    companies = data.companies;
  });
</script>

<h1>Create Companies</h1>

{#if form?.success}
  <div class="alert alert-success alert-dismissible fade show" role="alert">
    Company created successfully!
  </div>
{/if}

{#if form?.error}
  <div class="alert alert-danger alert-dismissible fade show" role="alert">
    {form.error}
  </div>
{/if}

<form class="mb-5" method="POST" action="?/createCompany" use:enhance>
  <div class="row mb-3">
    <div class="col">
      <label class="form-label" for="name">Name</label>
      <input class="form-control" id="name" name="name" type="text" required />
    </div>
  </div>
  <div class="row mb-3">
    <div class="col">
      <label class="form-label" for="email">email</label>
      <input
        class="form-control"
        id="email"
        name="email"
        type="text"
        required
      />
    </div>
  </div>
  <button type="submit" class="btn btn-primary">Submit</button>
</form>

<h1>All Companies</h1>
<table class="table">
  <thead>
    <tr>
      <th scope="col">Name</th>
      <th scope="col">E-Mail</th>
      <th scope="col">Id</th>
    </tr>
  </thead>
  <tbody>
    {#each companies as company}
      <tr>
        <td>{company.name}</td>
        <td>{company.email}</td>
        <td>{company.id}</td>
      </tr>
    {/each}
  </tbody>
</table>
